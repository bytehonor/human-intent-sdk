package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bytehonor.sdk.lang.spring.match.TextMatcher;
import com.bytehonor.sdk.lang.spring.match.WordMatcher;

public class IntentMatcher {

    private final String pattern;

    private final TextMatcher matcher;

    public IntentMatcher(String pattern, TextMatcher matcher) {
        this.pattern = pattern;
        this.matcher = matcher;
    }

    public String getPattern() {
        return pattern;
    }

    public TextMatcher getMatcher() {
        return matcher;
    }

    public static Builder builder(String pattern) {
        return new Builder(pattern);
    }

    public static class Builder {

        private final String pattern;

        private final List<WordMatcher> excluders;

        private final List<WordMatcher> includers;

        private Builder(String pattern) {
            this.pattern = pattern;
            this.excluders = new ArrayList<WordMatcher>();
            this.includers = new ArrayList<WordMatcher>();
        }

        public Builder exclude(String... words) {
            Objects.requireNonNull(words, "words");

            this.excluders.add(WordMatcher.of(words));
            return this;
        }

        public Builder include(String... words) {
            Objects.requireNonNull(words, "words");

            this.includers.add(WordMatcher.of(words));
            return this;
        }

        public IntentMatcher build() {
            return new IntentMatcher(pattern, new TextMatcher(excluders, includers));
        }
    }
}
