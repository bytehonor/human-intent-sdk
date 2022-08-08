package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public interface IntentResolver {

    public String pattern();

    public boolean match(IntentPayload payload);

    public IntentResult answer(IntentPayload payload, IntentSession session);
}
