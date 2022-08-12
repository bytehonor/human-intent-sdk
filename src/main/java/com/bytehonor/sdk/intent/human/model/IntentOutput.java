package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.Objects;

public class IntentOutput implements Serializable {

    private static final long serialVersionUID = -364312310080158140L;

    public static final String P = "p";

    public static final String A = "a";

    private String type;

    private String value;

    public static IntentOutput p(String value) {
        return new IntentOutput(P, value);
    }

    public static IntentOutput a(String value) {
        return new IntentOutput(A, value);
    }

    public IntentOutput(String type, String value) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(value, "value");
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
