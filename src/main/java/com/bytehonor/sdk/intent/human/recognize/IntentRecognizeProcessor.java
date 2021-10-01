package com.bytehonor.sdk.intent.human.recognize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentTarget;

public class IntentRecognizeProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(IntentRecognizeProcessor.class);

    /**
     * 必有值返回
     * 
     * @param request
     * @return
     */
    public static IntentTarget recognize(IntentRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        IntentTarget target = null;
        try {
            target = doRecognize(request);
        } catch (Exception e) {
            LOG.error("query:{}, uuid:{}, app:{}, error", request.getQuery(), request.getUuid(), request.getApp(), e);
        }
        if (target == null) {
            target = IntentTarget.undefined(request, request.getApp());
        }
        return target;
    }

    /**
     * 必有值返回
     * 
     * @param request
     * @return
     */
    private static IntentTarget doRecognize(IntentRequest request) {
        Objects.requireNonNull(request, "request");
        if (request.getSession().isAuto() == false) {
            return IntentTarget.undefined(request, IntentConstants.PUBLIC_STOP_AUTO);
        }

        if (StringObject.isEmpty(request.getQuery())) {
            return IntentTarget.undefined(request, request.getApp());
        }

        List<IntentTarget> list = Collections.synchronizedList(new ArrayList<IntentTarget>());
        IntentRecognizerFactory.list().parallelStream().forEach(handler -> {
            if (IntentConstants.ANY.equals(handler.app()) == false
                    && StringObject.equals(request.getApp(), handler.app()) == false) {
                return;
            }
            IntentTarget tar = handler.recoginze(request);
            if (tar.getScore() > 50) {
                LOG.info("{}, {}, {}", tar.getScore(), tar.getIntent(), tar.getRecognizer());
                list.add(tar);
            }
        });
        int max = 0;
        IntentTarget target = null;
        for (IntentTarget item : list) {
            if (item.getScore() > max) {
                max = item.getScore();
                target = item;
            }
        }

        if (target != null) {
            return target;
        }

        // 缺省的时候就放app，由应用的默认处理器去处理
        return IntentTarget.undefined(request, request.getApp());
    }
}
