package com.bytehonor.sdk.intent.human.resolve;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentTarget;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizeProcessor;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;

public final class IntentResolveProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(IntentResolveProcessor.class);

    /**
     * 必须有返回值
     * 
     * @param target
     * @return
     */
    public static IntentResult resolve(IntentTarget target) {
        Objects.requireNonNull(target, "target");
        Objects.requireNonNull(target.getSession(), "session");
        LOG.info("session id:{}, preIntent:{}, nowIntent:{}", target.getSession().getId(),
                target.getSession().getPreIntent(), target.getSession().getNowIntent());

        if (target.getSession().isAuto() == false) {
            // 停止自动应答
            return IntentResult.non(target);
        }

        if (IntentConstants.PUBLIC_AMBIGUOUS.equals(target.getIntent())) {
            // 含糊不清的
            return doAmbiguous(target);
        }

        IntentResolver handler = IntentResolverFactory.optional(target.getIntent());
        if (handler == null) {
            LOG.error("intent:{}, query:{}, uuid:{}, app:{} no handler", target.getIntent(), target.getQuery(),
                    target.getSession().getUuid(), target.getSession().getApp());
            return IntentResult.text(target, IntentConstants.TIP_HANDLER_NON);
        }
        IntentResult result = null;
        try {
            result = handler.resolve(target);
        } catch (Exception e) {
            LOG.error("query:{}, intent:{}, recognizer:{}, error", target.getQuery(), target.getIntent(),
                    target.getRecognizer(), e);
        }
        if (result == null) {
            result = IntentResult.text(target, IntentConstants.TIP_HANDLER_ERROR);
        }
        return result;
    }

    private static IntentResult doAmbiguous(IntentTarget target) {
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
}
