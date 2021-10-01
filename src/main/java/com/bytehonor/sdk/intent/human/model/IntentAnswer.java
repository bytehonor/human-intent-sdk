package com.bytehonor.sdk.intent.human.model;

public class IntentAnswer {

    public static final String IMAGE = "image";

    public static final String TEXT = "text";

    public static final String NON = "non";

    private String type;

    private String value;

    public static IntentAnswer image(String value) {
        return of(IMAGE, value);
    }

    public static IntentAnswer text(String value) {
        return of(TEXT, value);
    }

    public static IntentAnswer non() {
        return of(NON, "");
    }

    public static IntentAnswer of(String type, String value) {
        IntentAnswer answer = new IntentAnswer();
        answer.setType(type);
        answer.setValue(value);
        return answer;
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
