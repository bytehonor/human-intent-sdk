package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntentAnswer implements Serializable {

    private static final long serialVersionUID = 4943674754872794906L;

    private List<IntentOutput> list;

    public IntentAnswer() {
        list = new ArrayList<IntentOutput>();
    }

    public static IntentAnswer make() {
        return new IntentAnswer();
    }

    public IntentAnswer p(String text) {
        return add(IntentOutput.p(text));
    }

    public IntentAnswer a(String text) {
        return add(IntentOutput.a(text));
    }

    public IntentAnswer add(IntentOutput output) {
        Objects.requireNonNull(output, "output");
        list.add(output);
        return this;
    }

    public List<IntentOutput> getList() {
        return list;
    }

    public void setList(List<IntentOutput> list) {
        this.list = list;
    }

}
