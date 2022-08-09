package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class IntentResolverPool {

    private final Map<String, IntentResolver> resolvers;

    public IntentResolverPool() {
        resolvers = new HashMap<String, IntentResolver>();
    }

    public void add(IntentResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        resolvers.put(resolver.getClass().getSimpleName(), resolver);
    }

    public IntentResolver get(String name) {
        Objects.requireNonNull(name, "name");
        return resolvers.get(name);
    }

    public List<IntentResolver> all() {
        List<IntentResolver> list = new ArrayList<IntentResolver>();
        for (Entry<String, IntentResolver> item : resolvers.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }

}
