package com.bytehonor.sdk.intent.human;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.filter.IntentFilterFactory;
import com.bytehonor.sdk.intent.human.filter.IntentFilterProcessor;
import com.bytehonor.sdk.intent.human.filter.IntentRequestFilter;
import com.bytehonor.sdk.intent.human.filter.IntentResultFilter;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentTarget;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizeProcessor;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizerFactory;
import com.bytehonor.sdk.intent.human.resolve.IntentResolveProcessor;
import com.bytehonor.sdk.intent.human.resolve.IntentResolver;
import com.bytehonor.sdk.intent.human.resolve.IntentResolverFactory;
import com.bytehonor.sdk.lang.bytehonor.string.StringSplitUtils;

public final class HumanIntentProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentProcessor.class);

    public static IntentResult process(final IntentRequest request) {
        IntentFilterProcessor.before(request);
        IntentTarget target = IntentRecognizeProcessor.recognize(request);
        if (LOG.isInfoEnabled()) {
            LOG.info("query:{}, intent:{}, recognizer:{}, uuid:{}", request.getQuery(), target.getIntent(),
                    target.getRecognizer(), request.getUuid());
        }

        if (target.getSession().isAuto() == false) {
            // 停止自动应答
            return IntentResult.non(target);
        }

        if (IntentConstants.PUBLIC_AMBIGUOUS.equals(target.getIntent())) {
            // 含糊不清的
            return doAmbiguous(target);
        }

        IntentResult result = IntentResolveProcessor.resolve(target);
        IntentFilterProcessor.after(result);
        return result;
    }

    public static IntentResult doAmbiguous(IntentTarget target) {
        List<String> intents = StringSplitUtils.split(target.getSlotValue("intents"));
        List<String> patterns = IntentRecognizeProcessor.listPatterns(target.getSession().getApp(), intents);
        StringBuilder sb = new StringBuilder();
        sb.append(IntentConstants.TIP_HANDLER_AMBIGUOUS).append("\r\n");
        for (String pattern : patterns) {
            sb.append("\r\n");
            sb.append(pattern).append("?");
        }
        return IntentResult.text(target, sb.toString());
    }

    public static void setRecognizer(IntentRecognizer recognizer) {
        if (recognizer == null) {
            return;
        }
        IntentRecognizerFactory.put(recognizer);
    }

    public static void setResolver(IntentResolver resolver) {
        if (resolver == null) {
            return;
        }
        IntentResolverFactory.put(resolver);
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
