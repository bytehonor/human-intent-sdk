package com.bytehonor.sdk.intent.human.matcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.constant.CharConstants;
import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.match.WordMatcher;
import com.bytehonor.sdk.lang.spring.regex.PatternUtils;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringSliceUtils;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;

public class IntentMatcher {

    private static final int WORD_MIN_LENGTH = 1;

    private static final int CHINESE_MAX_LENGTH = 4;

    private final String pattern;

    private final int limit;

    /**
     * 排除的条件, 满足一组就排除 or
     */
    private final List<WordMatcher> excluders;

    /**
     * 满足的条件, 满足一组就可以 or
     */
    private final List<WordMatcher> includers;

    public IntentMatcher(String pattern, int limit, List<WordMatcher> excluders, List<WordMatcher> includers) {
        this.pattern = pattern;
        this.limit = limit;
        this.excluders = excluders;
        this.includers = includers;
    }

    public boolean match(String text) {
        if (SpringString.isEmpty(text)) {
            return false;
        }

        return match(text.length(), words(text));
    }

    public boolean match(int length, Set<String> words) {
        if (CollectionUtils.isEmpty(words)) {
            return false;
        }

        if (length > this.limit) {
            return false;
        }

        if (doMatch(words, this.excluders)) {
            return false;
        }
        return doMatch(words, this.includers);
    }

    public String getPattern() {
        return pattern;
    }

    public List<WordMatcher> getExcluders() {
        return excluders;
    }

    public List<WordMatcher> getIncluders() {
        return includers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("excluders:").append(excluders.size()).append(", includers:").append(includers.size());
        return sb.toString();
    }

    /**
     * 非中文按空格自然分割, 中文超过4个字的重新切割成4字以下的字块
     * 
     * @param text
     * @return
     */
    public static Set<String> words(String text) {
        // 只保留 英文,数字,汉字,空格
        text = IntentQuery.prepare(text);
        if (SpringString.isEmpty(text)) {
            return new HashSet<String>();
        }
        Set<String> raws = StringSplitUtils.toSet(text, CharConstants.BLANK);
        int size = raws.size();
        Set<String> result = new HashSet<String>(size * 10);
        Set<String> chineseWords = new HashSet<String>(size * 10);
        for (String raw : raws) {
            if (raw.length() < WORD_MIN_LENGTH) {
                continue;
            }

            if (PatternUtils.isLetter(raw)) {
                result.add(raw.toLowerCase()); // 纯英文,直接使用, 且小写
                continue;
            }
            if (PatternUtils.isNumber(raw)) {
                result.add(raw); // 纯数字,直接使用
                continue;
            }
            if (PatternUtils.isChinese(raw)) {
                chineseWords.add(raw); // 汉字块二次分割
                continue;
            }
        }
        if (CollectionUtils.isEmpty(chineseWords) == false) {
            for (String chinese : chineseWords) {
                if (chinese.length() < WORD_MIN_LENGTH) {
                    continue;
                }
                Set<String> parts = StringSliceUtils.slice(chinese, CHINESE_MAX_LENGTH);
                for (String part : parts) {
                    if (part.length() < WORD_MIN_LENGTH) {
                        continue;
                    }
                    result.add(part);
                }
            }
        }
        return result;
    }

    private static boolean doMatch(Set<String> words, List<WordMatcher> matchers) {
        if (CollectionUtils.isEmpty(words) || CollectionUtils.isEmpty(matchers)) {
            return false;
        }

        for (WordMatcher matcher : matchers) {
            if (matcher.match(words)) {
                return true;
            }
        }
        return false;
    }

    public static Builder builder(String pattern) {
        return new Builder(pattern);
    }

    public static class Builder {

        private final String pattern;

        private int limit;

        private final List<WordMatcher> excluders;

        private final List<WordMatcher> includers;

        private Builder(String pattern) {
            Objects.requireNonNull(pattern, "pattern");
            this.pattern = pattern;
            this.limit = pattern.length() * 30;
            this.excluders = new ArrayList<WordMatcher>();
            this.includers = new ArrayList<WordMatcher>();
        }

        public Builder limit(int limit) {
            if (limit < 1) {
                throw new SpringLangException("invalid limit:" + limit);
            }

            this.limit = limit;
            return this;
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
            return new IntentMatcher(pattern, limit, excluders, includers);
        }
    }
}
