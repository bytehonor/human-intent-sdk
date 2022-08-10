package com.bytehonor.sdk.intent.human;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.chat.ChatClient;
import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.constant.IntentPlatformEnum;
import com.bytehonor.sdk.intent.human.listener.IntentListenerThread;
import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.intent.human.resolver.AskAbilityIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.AskAgeIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.AskMusicIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.AskNameIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;
import com.bytehonor.sdk.intent.human.resolver.UnsupportIntentResolver;
import com.bytehonor.sdk.intent.human.worker.IntentWorker;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public final class HumanIntentRecognizer {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentRecognizer.class);

    private final IntentContext context;

    private final IntentWorker worker;

    public HumanIntentRecognizer(IntentContext context, IntentWorker worker) {
        Objects.requireNonNull(context, "context");
        Objects.requireNonNull(worker, "worker");

        this.context = context;
        this.worker = worker;
    }

    public static HumanIntentRecognizer create(String name, String platform, IntentWorker worker) {
        return create(new IntentContext(name, platform), worker);
    }

    public static HumanIntentRecognizer create(String name, IntentWorker worker) {
        return create(new IntentContext(name, IntentPlatformEnum.UNDEFINED.getKey()), worker);
    }

    public static HumanIntentRecognizer create(IntentContext context, IntentWorker worker) {
        HumanIntentRecognizer recognizer = new HumanIntentRecognizer(context, worker);
        recognizer.add(new UnsupportIntentResolver());
        recognizer.add(new AskMusicIntentResolver());
        recognizer.add(new AskAbilityIntentResolver());
        recognizer.add(new AskNameIntentResolver());
        recognizer.add(new AskAgeIntentResolver());
        return recognizer;
    }

    public IntentResult recognize(IntentRequest request) {
        Objects.requireNonNull(request, "request");

        IntentSession session = worker.get(request.getUuid());
        if (session == null) {
            session = IntentSession.init(request.getUuid(), context.getPlatform());
        }

        IntentResult result = doRecognize(request, session);

        session.setId(session.getId() + 1);
        session.setPreIntent(session.getNowIntent());
        session.setNowIntent(result.getResolver());
        session.setLastAt(System.currentTimeMillis());
        worker.put(request.getUuid(), session);

        result.setSession(session);

        IntentListenerThread.add(result);
        return result;
    }

    public HumanIntentRecognizer add(IntentResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        this.context.add(resolver);
        return this;
    }

    /**
     * 必有值返回
     * 
     * @param request
     * @return
     */
    private IntentResult doRecognize(IntentRequest request, IntentSession session) {
        Objects.requireNonNull(request, "request");

        long now = System.currentTimeMillis();
        if (session.isPaused() && (now - session.getLastAt() < TimeConstants.HOUR)) {
            return IntentResult.non(); // 返回空
        }

        IntentPayload payload = IntentPayload.of(request.getQuery());
        List<IntentResolver> list = doParse(payload, session);
        int size = list != null ? list.size() : 0;
        if (size == 0) {
            return IntentResult.chat(ChatClient.ask(request.getQuery(), request.getUuid()));
        }

        if (size > 1) {
            // 含糊不清的
            return doAmbiguous(list);
        }

        IntentResolver recognizer = list.get(0);
        String answers = recognizer.answer(payload, session, context);
        return IntentResult.of(recognizer.getClass().getSimpleName(), answers);
    }

    private static IntentResult doAmbiguous(List<IntentResolver> resolvers) {
        StringBuilder answers = new StringBuilder();
        answers.append(IntentConstants.TIP_HANDLER_AMBIGUOUS);
        for (IntentResolver resolver : resolvers) {
            answers.append(resolver.matcher().getPattern());
        }
        return IntentResult.of(IntentResult.AMBIGUOUS, answers.toString());
    }

    private List<IntentResolver> doParse(IntentPayload payload, IntentSession session) {
        Objects.requireNonNull(payload, "payload");
        if (SpringString.isEmpty(payload.getQuery())) {
            LOG.warn("query is empty");
            return new ArrayList<IntentResolver>();
        }

        List<IntentResolver> result = new ArrayList<IntentResolver>();
        List<IntentResolver> resolvers = context.getResolvers();
        for (IntentResolver resolver : resolvers) {
            if (doMatch(resolver.matcher(), payload)) {
                result.add(resolver);
            }
        }
        return result;
    }

    private static boolean doMatch(IntentMatcher matcher, IntentPayload payload) {
        return matcher.match(payload.getWords());
    }
}
