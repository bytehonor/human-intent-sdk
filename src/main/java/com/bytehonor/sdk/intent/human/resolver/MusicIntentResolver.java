package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.constant.MusicUrlEnum;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class MusicIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public MusicIntentResolver() {
        this.matcher = IntentMatcher.builder("播放音乐").include("放", "歌").include("播放", "音乐").build();
    }

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session, IntentContext context) {
        String mp3 = MusicUrlEnum.random().getUrl();
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text("嗯，" + matcher.getPattern()));
        answers.add(IntentAnswer.media(mp3));
        return IntentResult.of(answers);
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

}
