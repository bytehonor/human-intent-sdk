package com.bytehonor.sdk.intent.human.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.bytehonor.sdk.intent.human.exception.HumanIntentException;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;

public class IntentContext {

    private final String name;

    private final String platform;

    private final List<IntentResolver> resolvers;

    private final Set<String> patterns;

    public IntentContext(String name, String platform) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(platform, "platform");
        
        this.name = name;
        this.platform = platform;
        this.resolvers = new ArrayList<IntentResolver>();
        this.patterns = new HashSet<String>();
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public List<IntentResolver> getResolvers() {
        return resolvers;
    }

    public void add(IntentResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");
        String pattern = resolver.matcher().getPattern();
        if (patterns.contains(pattern)) {
            throw new HumanIntentException("already exists, pattern:" + pattern);
        }
        this.resolvers.add(resolver);
        this.patterns.add(pattern);
    }
}