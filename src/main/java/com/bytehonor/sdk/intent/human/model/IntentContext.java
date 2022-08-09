package com.bytehonor.sdk.intent.human.model;

import java.util.Objects;

import com.bytehonor.sdk.intent.human.resolver.IntentResolverPool;

public class IntentContext {

    private final String name;

    private final IntentResolverPool pool;

    public IntentContext(String name) {
        Objects.requireNonNull(name, "name");
        this.name = name;
        this.pool = new IntentResolverPool();
    }

    public String getName() {
        return name;
    }

    public IntentResolverPool getPool() {
        return pool;
    }
}
