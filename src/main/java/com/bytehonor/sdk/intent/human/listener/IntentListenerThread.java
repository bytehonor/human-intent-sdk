package com.bytehonor.sdk.intent.human.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.framework.lang.thread.LinkedBlockingThread;
import com.bytehonor.sdk.framework.lang.thread.QueueConsumer;
import com.bytehonor.sdk.intent.human.model.IntentResult;

public class IntentListenerThread {

    private static final Logger LOG = LoggerFactory.getLogger(IntentListenerThread.class);

    private final LinkedBlockingThread<IntentResult> thread;

    private IntentListenerThread() {

        thread = LinkedBlockingThread.create(new QueueConsumer<IntentResult>() {

            @Override
            public void consume(IntentResult payload) {
                doProcess(payload);
            }

        }).mount(IntentListenerThread.class);

        thread.start();
    }

    private static class LazyHolder {
        private static IntentListenerThread INSTANCE = new IntentListenerThread();
    }

    private static IntentListenerThread self() {
        return LazyHolder.INSTANCE;
    }

    public static void add(IntentResult payload) {
        if (payload == null) {
            LOG.warn("add payload null");
            return;
        }
        self().thread.add(payload);
    }

    private void doProcess(IntentResult result) {
        try {
            filterResult(result);
        } catch (Exception e) {
            LOG.error("filterResult error", e);
        }
    }

    private static void filterResult(final IntentResult result) {
        List<IntentListener> listeners = IntentListenerFactory.list();
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        }
        for (IntentListener listener : listeners) {
            listener.chain(result);
        }
    }
}
