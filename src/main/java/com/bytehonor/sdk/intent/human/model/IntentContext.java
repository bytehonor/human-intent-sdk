package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.bytehonor.sdk.intent.human.exception.HumanIntentException;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;

public class IntentContext implements Serializable {

    private static final long serialVersionUID = -774487225071783615L;

    private final boolean ack;

    private final String app;

    private final String name;

    private final String platform;

    private final List<IntentResolver> resolvers;

    private final Set<String> privates;

    private final Set<String> publics;

    public IntentContext(boolean ack, String app, String name, String platform) {
        Objects.requireNonNull(app, "app");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(platform, "platform");

        this.ack = ack;
        this.app = app;
        this.name = name;
        this.platform = platform;
        this.resolvers = new ArrayList<IntentResolver>();
        this.privates = new HashSet<String>();
        this.publics = new HashSet<String>();
    }

    public boolean isAck() {
        return ack;
    }

    public String getApp() {
        return app;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public List<IntentResolver> getResolvers() {
        return resolvers;
    }

    public Set<String> getPrivates() {
        return privates;
    }

    public Set<String> getPublics() {
        return publics;
    }

    public void add(IntentResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");
        String pattern = resolver.matcher().getPattern();
        if (privates.contains(pattern)) {
            throw new HumanIntentException("privates exists, pattern:" + pattern);
        }
        if (publics.contains(pattern)) {
            throw new HumanIntentException("publics exists, pattern:" + pattern);
        }
        this.resolvers.add(resolver);
        if (resolver.privated()) {
            this.privates.add(pattern);
        } else {
            this.publics.add(pattern);
        }
    }
}
