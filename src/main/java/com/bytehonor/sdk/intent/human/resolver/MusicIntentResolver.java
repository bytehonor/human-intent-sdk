package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class MusicIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public MusicIntentResolver() {
        this.matcher = IntentMatcher.builder("播放音乐").include("放", "歌").include("音乐").build();
    }

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session) {
        return null;
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

}
