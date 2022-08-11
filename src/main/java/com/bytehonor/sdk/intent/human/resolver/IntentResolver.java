package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswers;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public interface IntentResolver {

    public boolean privated();

    public IntentMatcher matcher();

    public IntentAnswers answer(IntentPayload payload, IntentSession session, IntentContext context);
}
