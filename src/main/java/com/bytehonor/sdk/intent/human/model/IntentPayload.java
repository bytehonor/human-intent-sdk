package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;

public class IntentPayload implements Serializable {

    private static final long serialVersionUID = -2121896383034921632L;

    private String query;

    private Set<String> words;

    private int length;
    
    public IntentPayload() {
        this.query = "";
        this.length = 0;
    }

    public static IntentPayload of(String query) {
        Objects.requireNonNull(query, "query");
        IntentPayload payload = new IntentPayload();
        payload.setQuery(query);
        payload.setWords(IntentMatcher.words(query));
        payload.setLength(query.length());
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
