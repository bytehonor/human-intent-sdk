package com.bytehonor.sdk.intent.human.resolver;

import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public interface IntentResolver {

    public boolean privated();

    public IntentMatcher matcher();

    public List<IntentAnswer> answer(IntentPayload payload, IntentSession session, IntentContext context);
}
