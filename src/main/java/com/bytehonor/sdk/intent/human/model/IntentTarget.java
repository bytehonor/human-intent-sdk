package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;
import com.bytehonor.sdk.lang.bytehonor.util.ListJoinUtils;

public class IntentTarget {

    private String query;

    private String intent;

    private IntentSession session;

    private int score;

    private String recognizer;

    private List<IntentSlot> slots;

    public IntentTarget() {
        this(null, null, 0, null, null, null);
    }

    public IntentTarget(String query, IntentSession session, int score, String recognizer, String intent,
            List<IntentSlot> slots) {
        this.query = query;
        this.session = session;
        this.score = score;
        this.recognizer = recognizer;
        this.intent = intent;
        this.slots = slots;
    }

    public static IntentTarget manual(IntentRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        request.getSession().setAuto(false);
        return new IntentTarget(request.getQuery(), request.getSession(), 100, "system",
                IntentConstants.PUBLIC_STOP_AUTO, null);
    }

    public static IntentTarget undefined(IntentRequest request, String app) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        Objects.requireNonNull(app, "app");
        request.getSession().setAuto(true);
        return new IntentTarget(request.getQuery(), request.getSession(), 100, "system", app, null);
    }

    public static IntentTarget no(IntentRequest request, IntentRecognizer handler) {
        return auto(request, 0, handler, null);
    }

    public static IntentTarget ambiguous(IntentRequest request, List<IntentTarget> targets) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getSession(), "session");
        Objects.requireNonNull(targets, "targets");
        request.getSession().setAuto(true);
        List<String> intents = new ArrayList<String>();
        for (IntentTarget target : targets) {
            intents.add(target.getIntent());
        }
        List<IntentSlot> slots = new ArrayList<IntentSlot>();
        slots.add(new IntentSlot("intents", ListJoinUtils.joinString(intents)));
        return new IntentTarget(request.getQuery(), request.getSession(), 100, "system",
                IntentConstants.PUBLIC_AMBIGUOUS, slots);
    }

    public static IntentTarget auto(IntentRequest request, int score, IntentRecognizer handler,
            List<IntentSlot> slots) {
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

    public List<IntentSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<IntentSlot> slots) {
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

}
