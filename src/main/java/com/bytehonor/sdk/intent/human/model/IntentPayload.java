package com.bytehonor.sdk.intent.human.model;

import java.util.Objects;
import java.util.Set;

import com.bytehonor.sdk.lang.spring.match.TextMatcher;

public class IntentPayload {

    private String query;

    private Set<String> words;

    public static IntentPayload of(String query) {
        Objects.requireNonNull(query, "query");
        IntentPayload payload = new IntentPayload();
        payload.setQuery(query);
        payload.setWords(TextMatcher.words(query));
        return payload;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

}
