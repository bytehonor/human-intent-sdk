package com.bytehonor.sdk.intent.human.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntentListenerFactory {

    private static List<IntentListener> LISTENERS = new ArrayList<IntentListener>();

    public static void add(IntentListener listener) {
        Objects.requireNonNull(listener, "listener");

        LISTENERS.add(listener);
    }

    public static List<IntentListener> list() {
        return LISTENERS;
    }
}
