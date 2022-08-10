package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.constant.MusicUrlEnum;
import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class AskMusicIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public AskMusicIntentResolver() {
        this.matcher = IntentMatcher.builder("播放音乐").include("放", "歌").include("来一首", "歌").include("音乐").build();
    }

    @Override
    public String answer(IntentPayload payload, IntentSession session, IntentContext context) {
        String mp3 = MusicUrlEnum.random().getUrl();
        return mp3;
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

    @Override
    public boolean privated() {
        return false;
    }

}