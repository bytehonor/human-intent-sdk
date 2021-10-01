package com.bytehonor.sdk.intent.human.resolve;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentTarget;

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
}
