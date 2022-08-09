package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.List;

public class IntentResult {

    private String resolver;

    private List<IntentAnswer> answers;

    public static IntentResult of(String resolver, List<IntentAnswer> answers) {
        IntentResult result = new IntentResult();
        result.setResolver(resolver);
        result.setAnswers(answers);
        return result;
    }

    public static IntentResult non() {
        return of("NON", new ArrayList<IntentAnswer>());
    }

    public static IntentResult one(String resolver, String text) {
        ArrayList<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(text));
        return of(resolver, answers);
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public List<IntentAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<IntentAnswer> answers) {
        this.answers = answers;
    }

}
