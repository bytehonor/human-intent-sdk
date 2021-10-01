package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;
import com.bytehonor.sdk.intent.human.util.IntentSlotBuilder;
import com.bytehonor.sdk.lang.bytehonor.util.ListJoinUtils;

public class IntentTarget {

    private String query;

    private String intent;

    private IntentSession session;

    private int score;

    private String recognizer;

    private Map<String, String> slots;

    public IntentTarget() {
        this(null, null, IntentConstants.SCORE_MIN, null, null, null);
    }

    public IntentTarget(String query, IntentSession session, int score, String recognizer, String intent,
            Map<String, String> slots) {
        this.query = query;
        this.session = session;
        this.score = score;
        this.recognizer = recognizer;
        this.intent = intent;
        this.slots = slots;
    }

    /**
     * 人工处理
     * 
     * @param request
     * @return
     */
    public static IntentTarget manual(IntentRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        request.getSession().setAuto(false);
        return new IntentTarget(request.getQuery(), request.getSession(), IntentConstants.SCORE_MAX,
                IntentConstants.SYSTEM, IntentConstants.PUBLIC_STOP_AUTO, null);
    }

    /**
     * 未定义, 归属app
     * 
     * @param request
     * @param app
     * @return
     */
    public static IntentTarget undefined(IntentRequest request, String app) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        Objects.requireNonNull(app, "app");
        request.getSession().setAuto(true);
        return new IntentTarget(request.getQuery(), request.getSession(), IntentConstants.SCORE_MAX,
                IntentConstants.SYSTEM, app, null);
    }

    public static IntentTarget deny(IntentRequest request, IntentRecognizer handler) {
        return auto(request, IntentConstants.SCORE_MIN, handler, null);
    }

    /**
     * 模糊不清
     * 
     * @param request
     * @param targets
     * @return
     */
    public static IntentTarget ambiguous(IntentRequest request, List<IntentTarget> targets) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        Objects.requireNonNull(targets, "targets");
        request.getSession().setAuto(true);
        List<String> intents = new ArrayList<String>();
        for (IntentTarget target : targets) {
            intents.add(target.getIntent());
        }

        Map<String, String> slots = IntentSlotBuilder.create().put("intents", ListJoinUtils.joinString(intents))
                .build();
        return new IntentTarget(request.getQuery(), request.getSession(), IntentConstants.SCORE_MAX,
                IntentConstants.SYSTEM, IntentConstants.PUBLIC_AMBIGUOUS, slots);
    }

    /**
     * 自动
     * 
     * @param request
     * @param score
     * @param handler
     * @param slots
     * @return
     */
    public static IntentTarget auto(IntentRequest request, int score, IntentRecognizer handler,
            Map<String, String> slots) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        Objects.requireNonNull(handler, "handler");
        request.getSession().setAuto(true);
        return new IntentTarget(request.getQuery(), request.getSession(), score, handler.getClass().getSimpleName(),
                handler.intent(), slots);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRecognizer() {
        return recognizer;
    }

    public void setRecognizer(String recognizer) {
        this.recognizer = recognizer;
    }

    public Map<String, String> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, String> slots) {
        this.slots = slots;
    }

    public IntentSession getSession() {
        return session;
    }

    public void setSession(IntentSession session) {
        this.session = session;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getSlotValue(String key) {
        if (StringObject.isEmpty(key)) {
            return null;
        }
        return slots != null ? slots.get(key) : null;
    }
}
