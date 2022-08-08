package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.lang.spring.match.TextMatcher;

public abstract class AbstractIntentResolver implements IntentResolver {

    protected TextMatcher matcher;
    
    @Override
    public final String intent() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String pattern() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean match(IntentPayload payload) {
        return matcher.match(payload.getWords());
    }

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session) {
        // TODO Auto-generated method stub
        return null;
    }

}
