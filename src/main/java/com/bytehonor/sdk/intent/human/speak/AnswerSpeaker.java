package com.bytehonor.sdk.intent.human.speak;

import java.util.Objects;

import com.bytehonor.sdk.intent.human.model.IntentAnswer;

public class AnswerSpeaker {

    public static String speak(IntentAnswer answer) {
        return speak(answer, new SimpleAnswerSpeaker());
    }

    public static String speak(IntentAnswer answer, AnswerSpeak speaker) {
        Objects.requireNonNull(answer, "answer");
        Objects.requireNonNull(speaker, "speaker");
        return speaker.speak(answer);
    }
}
