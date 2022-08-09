package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.List;

public class IntentResult {

    private List<IntentAnswer> answers;

    public static IntentResult of(List<IntentAnswer> answers) {
        IntentResult result = new IntentResult();
        result.setAnswers(answers);
        return result;
    }

    public static IntentResult non() {
        IntentResult result = new IntentResult();
        result.setAnswers(new ArrayList<IntentAnswer>());
        return result;
    }

    public static IntentResult one(String text) {
        IntentResult result = new IntentResult();
        ArrayList<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(text));
        result.setAnswers(answers);
        return result;
    }

    public List<IntentAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<IntentAnswer> answers) {
        this.answers = answers;
    }

}
