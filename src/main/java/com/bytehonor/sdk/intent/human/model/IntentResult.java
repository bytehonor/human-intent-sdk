package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;

public class IntentResult implements Serializable {

    private static final long serialVersionUID = 5281736029229466964L;

    public static final String NON = "Non";

    public static final String EMPTY = "Empty";

    public static final String AMBIGUOUS = "Ambiguous";

    public static final String CHAT = "Chat";

    private String resolver;

    private String answer;

    private IntentSession session;

    public static IntentResult of(String resolver, String answer) {
        IntentResult result = new IntentResult();
        result.setResolver(resolver);
        result.setAnswer(answer);
        return result;
    }

    public static IntentResult non() {
        return of(NON, "");
    }

    public static IntentResult empty() {
        return of(EMPTY, "");
    }

    public static IntentResult chat(String text) {
        return one(CHAT, text);
    }

    public static IntentResult one(String resolver, String text) {
        return of(resolver, text);
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public IntentSession getSession() {
        return session;
    }

    public void setSession(IntentSession session) {
        this.session = session;
    }

}
