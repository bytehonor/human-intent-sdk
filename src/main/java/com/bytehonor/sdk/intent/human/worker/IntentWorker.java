package com.bytehonor.sdk.intent.human.worker;

import com.bytehonor.sdk.intent.human.model.IntentSession;

public interface IntentWorker {

    public IntentSession get(String uuid);

    public void put(IntentSession session);
}
