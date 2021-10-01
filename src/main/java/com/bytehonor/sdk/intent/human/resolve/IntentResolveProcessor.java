package com.bytehonor.sdk.intent.human.resolve;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.filter.IntentFilterProcessor;
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
        if (IntentConstants.PUBLIC_STOP_AUTO.equals(target.getIntent())) {
            // 停止自动应答
            return IntentResult.non(target);
        }

        IntentResolver handler = IntentResolverFactory.optional(target.getIntent());
        if (handler == null) {
            LOG.error("intent:{}, query:{}, uuid:{}, app:{} no handler", target.getIntent(), target.getQuery(),
                    target.getSession().getUuid(), target.getSession());
            return IntentResult.text(target, "收到");
        }
        try {
            IntentResult result = handler.resolve(target);
            IntentFilterProcessor.chainResult(result);
            return result;
        } catch (Exception e) {
            LOG.error("query:{}, intent:{}, recognizer:{}, error", target.getQuery(), target.getIntent(),
                    target.getRecognizer(), e);
            return IntentResult.text(target, "出错了");
        }
    }
}
