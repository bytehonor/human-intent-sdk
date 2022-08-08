package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class MusicIntentResolver implements IntentResolver {

    private IntentMatcher matcher;

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String pattern() {
        return matcher.getPattern();
    }

    @Override
    public boolean match(IntentPayload payload) {
        // TODO Auto-generated method stub
        return false;
    }

}
