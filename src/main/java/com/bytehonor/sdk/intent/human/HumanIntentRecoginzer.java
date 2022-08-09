package com.bytehonor.sdk.intent.human;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;
import com.bytehonor.sdk.intent.human.resolver.MusicIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.WeixinUnsupportIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.WhatCanDoIntentResolver;
import com.bytehonor.sdk.intent.human.resolver.WhoIamIntentResolver;
import com.bytehonor.sdk.intent.human.worker.IntentWorker;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public final class HumanIntentRecoginzer {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentRecoginzer.class);

    private final IntentContext context;

    public HumanIntentRecoginzer(String name) {
        Objects.requireNonNull(name, "name");
        this.context = new IntentContext(name);
    }

    public static HumanIntentRecoginzer create(String name) {
        HumanIntentRecoginzer recognizer = new HumanIntentRecoginzer(name);
        recognizer.add(new MusicIntentResolver());
        recognizer.add(new WhatCanDoIntentResolver());
        recognizer.add(new WhoIamIntentResolver());
        recognizer.add(new WeixinUnsupportIntentResolver());
        return recognizer;
    }

    public IntentResult process(IntentRequest request, IntentWorker worker) {
        // IntentFilterProcessor.before(request);
        IntentResult result = doRecognize(request, worker);
        // IntentFilterProcessor.after(result);
        return result;
    }

    public HumanIntentRecoginzer add(IntentResolver resolver) {
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
    private IntentResult doRecognize(IntentRequest request, IntentWorker worker) {
        Objects.requireNonNull(request, "request");

        IntentPayload payload = IntentPayload.of(request.getQuery());
        IntentSession session = worker.get(request.getUuid());

        List<IntentResolver> list = doParse(payload, session);
        int size = list != null ? list.size() : 0;
        if (size == 0) {
            return IntentResult.non(); // 返回空
        }

        if (size > 1) {
            // 含糊不清的
            return doAmbiguous(list);
        }

        IntentResolver recognizer = list.get(0);
        List<IntentAnswer> answers = recognizer.answer(payload, session, context);
        return IntentResult.of(recognizer.getClass().getSimpleName(), answers);
    }

    private static IntentResult doAmbiguous(List<IntentResolver> resolvers) {
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(IntentConstants.TIP_HANDLER_AMBIGUOUS));
        for (IntentResolver resolver : resolvers) {
            answers.add(IntentAnswer.text(resolver.matcher().getPattern()));
        }
        return IntentResult.of("Ambiguous", answers);
    }

    private List<IntentResolver> doParse(IntentPayload payload, IntentSession session) {
        Objects.requireNonNull(payload, "payload");
        long now = System.currentTimeMillis();
        if (session.isAuto() == false && (now - session.getPreTime() < TimeConstants.HOUR)) {
            return new ArrayList<IntentResolver>();
        }

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
