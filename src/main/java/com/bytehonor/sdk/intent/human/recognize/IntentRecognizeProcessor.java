package com.bytehonor.sdk.intent.human.recognize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.define.bytehonor.constant.TimeConstants;
import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentTarget;
import com.bytehonor.sdk.intent.human.util.IntentRecognizeUtils;

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
        target.getSession().setNowIntent(target.getIntent());
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
        long now = System.currentTimeMillis();
        if (request.getSession().isAuto() == false && (now - request.getSession().getPreTime() < TimeConstants.HOUR)) {
            return IntentTarget.manual(request);
        }

        if (StringObject.isEmpty(request.getQuery())) {
            LOG.warn("query is empty, uuid:{}, app:{}", request.getUuid(), request.getApp());
            return IntentTarget.undefined(request, request.getApp());
        }

        List<IntentRecognizer> recognizers = IntentRecognizerFactory.list(request.getApp());
        if (CollectionUtils.isEmpty(recognizers)) {
            LOG.warn("query:{}, app:{} no recognizers", request.getQuery(), request.getApp());
            return IntentTarget.undefined(request, request.getApp());
        }
        List<IntentTarget> list = Collections.synchronizedList(new ArrayList<IntentTarget>());
        recognizers.parallelStream().forEach(recognizer -> {
            IntentTarget tar = recognizer.recoginze(request);
            if (tar.getScore() > 50) {
                LOG.info("{}, {}, {}", tar.getScore(), tar.getIntent(), tar.getRecognizer());
                list.add(tar);
            }
        });
        int size = list.size();
        if (size == 1) {
            return list.get(0);
        }
        if (size > 1) {
            // 多个目标,模糊不清也是一个目标,进行二次提问
            return IntentTarget.ambiguous(request, list);
        }
        // 缺省的时候就放app，由应用的默认处理器去处理
        return IntentTarget.undefined(request, request.getApp());
    }

    public static List<String> findPatterns(String app, List<String> intents) {
        List<String> list = new ArrayList<String>();
        for (String intent : intents) {
            IntentRecognizer recognizer = IntentRecognizerFactory.optional(intent);
            if (IntentRecognizeUtils.isAppMatch(app, recognizer) == false) {
                continue;
            }
            if (StringObject.isEmpty(recognizer.pattern()) == false) {
                list.add(recognizer.pattern());
            }
        }
        return list;
    }
}
