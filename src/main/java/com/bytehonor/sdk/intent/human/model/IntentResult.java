package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.List;

public class IntentResult {

    private String intent;

    private List<IntentAnswer> answers;

    public static IntentResult of(String intent, List<IntentAnswer> answers) {
        IntentResult result = new IntentResult();
        result.setIntent(intent);
        result.setAnswers(answers);
        return result;
    }

    public static IntentResult non() {
        return of("non", new ArrayList<IntentAnswer>());
    }

    public static IntentResult one(String intent, String text) {
        ArrayList<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(text));
        return of(intent, answers);
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<IntentAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<IntentAnswer> answers) {
        this.answers = answers;
    }

}
