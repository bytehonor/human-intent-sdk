package com.bytehonor.sdk.intent.human.model;

import java.util.List;

import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;

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

    public static IntentTarget undefined(IntentRequest request, String intent) {
        return make(request, 100, "system", intent, null);
    }

    public static IntentTarget make(IntentRequest request, int score, String recognizer, String intent,
            List<IntentSlot> slots) {
        return new IntentTarget(request.getQuery(), request.getSession(), score, recognizer, intent, slots);
    }

    public static IntentTarget done(IntentRequest request, int score, IntentRecognizer handler) {
        return make(request, score, handler.getClass().getSimpleName(), handler.intent(), null);
    }

    public static IntentTarget done(IntentRequest request, int score, IntentRecognizer handler,
            List<IntentSlot> slots) {
        return make(request, score, handler.getClass().getSimpleName(), handler.intent(), slots);
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
