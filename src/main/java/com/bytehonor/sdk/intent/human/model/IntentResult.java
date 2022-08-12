package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;

public class IntentResult implements Serializable {

    private static final long serialVersionUID = 5281736029229466964L;

    public static final String NON = "Non";

    public static final String AMBIGUOUS = "Ambiguous";

    public static final String CHAT = "Chat";

    private String query;

    private String resolver;

    private IntentAnswers answers;

    private IntentSession session;

    public IntentResult() {
    }

    public static IntentResult of(String query, String resolver, IntentAnswers answers) {
        IntentResult result = new IntentResult();
        result.setQuery(query);
        result.setResolver(resolver);
        result.setAnswers(answers);
        return result;
    }

    public static IntentResult non(String query) {
        return of(query, NON, IntentAnswers.make());
    }

    public static IntentResult chat(String query, String text) {
        return of(query, CHAT, IntentAnswers.make().p(text));
    }

    public static IntentResult ambiguous(String query, IntentAnswers answers) {
        return of(query, AMBIGUOUS, answers);
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

    public IntentAnswers getAnswers() {
        return answers;
    }

    public void setAnswers(IntentAnswers answers) {
        this.answers = answers;
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

        private IntentAnswers answers;

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

        public Builder answers(IntentAnswers answers) {
            this.answers = answers;
            return this;
        }

        public Builder session(IntentSession session) {
            this.session = session;
            return this;
        }

        public IntentResult build() {
            IntentResult result = of(query, resolver, answers);
            result.setSession(session);
            return result;
        }
    }

}
