package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntentAnswers implements Serializable {

    private static final long serialVersionUID = 4943674754872794906L;

    private List<IntentAnswer> list;

    public IntentAnswers() {
        list = new ArrayList<IntentAnswer>();
    }

    public static IntentAnswers make() {
        return new IntentAnswers();
    }

    public IntentAnswers p(String text) {
        return add(IntentAnswer.p(text));
    }

    public IntentAnswers a(String text) {
        return add(IntentAnswer.a(text));
    }

    public IntentAnswers add(IntentAnswer answer) {
        Objects.requireNonNull(answer, "answer");
        list.add(answer);
        return this;
    }

    public List<IntentAnswer> getList() {
        return list;
    }

    public void setList(List<IntentAnswer> list) {
        this.list = list;
    }

}
