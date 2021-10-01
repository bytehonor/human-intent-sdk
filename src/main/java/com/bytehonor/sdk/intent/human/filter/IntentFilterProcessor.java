package com.bytehonor.sdk.intent.human.filter;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class IntentFilterProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(IntentFilterProcessor.class);

    public static void before(IntentRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getUuid(), "uuid");
        Objects.requireNonNull(request.getApp(), "app");
        LOG.info("query:{}, uuid:{}, app:{}", request.getQuery(), request.getUuid(), request.getApp());
        try {
            filterRequest(request);
            if (request.getSession() == null) {
                LOG.error("request query:{} uuid:{} app:{}, no session", request.getQuery(), request.getUuid(),
                        request.getApp());
                request.setSession(IntentSession.init(request.getUuid(), request.getApp()));
            }
        } catch (Exception e) {
            LOG.error("query:{}, uuid:{}, app:{}, prepare error", request.getQuery(), request.getUuid(),
                    request.getApp(), e);
        }
    }

    private static void filterRequest(final IntentRequest request) {
        List<IntentRequestFilter> fiters = IntentFilterFactory.request();
        if (CollectionUtils.isEmpty(fiters)) {
            LOG.warn("IntentRequest filters empty");
            return;
        }
        for (IntentRequestFilter filter : fiters) {
            filter.chain(request);
        }
    }

    public static void after(final IntentResult result) {
        Objects.requireNonNull(result, "result");
        try {
            filterResult(result);
        } catch (Exception e) {
            LOG.error("filterResult error", e);
        }
    }

    private static void filterResult(final IntentResult result) {
        List<IntentResultFilter> fiters = IntentFilterFactory.result();
        if (CollectionUtils.isEmpty(fiters)) {
            LOG.warn("IntentResult filters empty");
            return;
        }
        for (IntentResultFilter filter : fiters) {
            filter.chain(result);
        }
    }
}
