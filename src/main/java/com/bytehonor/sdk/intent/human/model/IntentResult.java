package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;

public class IntentResult implements Serializable {

    private static final long serialVersionUID = 5281736029229466964L;

    public static final String NON = "Non";

    public static final String AMBIGUOUS = "Ambiguous";

    public static final String CHAT = "Chat";

    private String query;

    private String resolver;

    private IntentAnswer answer;

    private IntentSession session;

    public IntentResult() {
    }

    public static IntentResult of(String query, String resolver, IntentAnswer answer) {
        IntentResult result = new IntentResult();
        result.setQuery(query);
        result.setResolver(resolver);
        result.setAnswer(answer);
        return result;
    }

    public static IntentResult non(String query) {
        return of(query, NON, IntentAnswer.make());
    }

    public static IntentResult chat(String query, String text) {
        return of(query, CHAT, IntentAnswer.make().p(text));
    }

    public static IntentResult ambiguous(String query, IntentAnswer answer) {
        return of(query, AMBIGUOUS, answer);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public IntentAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(IntentAnswer answer) {
        this.answer = answer;
    }

    public IntentSession getSession() {
        return session;
    }

    public void setSession(IntentSession session) {
        this.session = session;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String query;

        private String resolver;

        private IntentAnswer answer;

        private IntentSession session;

        private Builder() {
        }

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder resolver(String resolver) {
            this.resolver = resolver;
            return this;
        }

        public Builder answer(IntentAnswer answer) {
            this.answer = answer;
            return this;
        }

        public Builder session(IntentSession session) {
            this.session = session;
            return this;
        }

        public IntentResult build() {
            IntentResult result = of(query, resolver, answer);
            result.setSession(session);
            return result;
        }
    }

}
