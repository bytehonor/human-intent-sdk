package com.bytehonor.sdk.intent.human;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.filter.IntentFilterFactory;
import com.bytehonor.sdk.intent.human.filter.IntentFilterProcessor;
import com.bytehonor.sdk.intent.human.filter.IntentRequestFilter;
import com.bytehonor.sdk.intent.human.filter.IntentResultFilter;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.intent.human.resolver.IntentRecognizerFactory;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;
import com.bytehonor.sdk.intent.human.resolver.IntentResolverPool;
import com.bytehonor.sdk.intent.human.worker.IntentWorker;
import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.match.TextMatcher;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public final class HumanIntentRecoginzer {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentRecoginzer.class);

    private String app;

    private TextMatcher matcher;

    private final IntentResolverPool pool;

    public HumanIntentRecoginzer() {
        this.pool = new IntentResolverPool();
    }

    public IntentResult process(IntentRequest request, IntentWorker worker) {
        IntentFilterProcessor.before(request);
        IntentResult result = recognize(request, worker);
        IntentFilterProcessor.after(result);
        return result;
    }

    /**
     * 必有值返回
     * 
     * @param request
     * @return
     */
    public IntentResult recognize(IntentRequest request, IntentWorker worker) {
        Objects.requireNonNull(request, "request");

        IntentPayload payload = IntentPayload.of(request.getQuery());
        IntentSession session = worker.get(request.getUuid());

        List<IntentResolver> list = doRecognize(payload, session);
        int size = list != null ? list.size() : 0;
        if (size == 0) {
            return null; // 返回空
        }

        if (size > 1) {
            // 含糊不清的
            return doAmbiguous(list);
        }

        IntentResolver recognizer = list.get(0);
        return recognizer.answer(payload, session);
    }

    private static IntentResult doAmbiguous(List<IntentResolver> recognizers) {
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(IntentConstants.TIP_HANDLER_AMBIGUOUS));
        for (IntentResolver recognizer : recognizers) {
            answers.add(IntentAnswer.text(recognizer.pattern()));
        }
        return IntentResult.of(answers);
    }

    private List<IntentResolver> doRecognize(IntentPayload payload, IntentSession session) {
        Objects.requireNonNull(payload, "payload");
        long now = System.currentTimeMillis();
        if (session.isAuto() == false && (now - session.getPreTime() < TimeConstants.HOUR)) {
            return new ArrayList<IntentResolver>();
        }

        if (SpringString.isEmpty(payload.getQuery())) {
            LOG.warn("query is empty");
            return new ArrayList<IntentResolver>();
        }

        List<IntentResolver> resolvers = new ArrayList<IntentResolver>();
        List<IntentResolver> all = pool.all();
        for (IntentResolver item : all) {
            if (item.match(payload)) {
                resolvers.add(item);
            }
        }
        return resolvers;
    }

    public static void setRecognizer(IntentResolver recognizer) {
        if (recognizer == null) {
            return;
        }
        IntentRecognizerFactory.put(recognizer);
    }

    public static void setFilter(IntentRequestFilter filter) {
        if (filter == null) {
            return;
        }
        IntentFilterFactory.putRequest(filter);
    }

    public static void setFilter(IntentResultFilter filter) {
        if (filter == null) {
            return;
        }
        IntentFilterFactory.putResult(filter);
    }
}
