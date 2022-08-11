package com.bytehonor.sdk.intent.human.speak;

import java.util.Objects;

import com.bytehonor.sdk.intent.human.model.IntentAnswers;

public class AnswerSpeaker {

    public static String speak(IntentAnswers answers) {
        return speak(answers, new SimpleAnswerSpeaker());
    }

    public static String speak(IntentAnswers answers, AnswerSpeak speaker) {
        Objects.requireNonNull(answers, "answers");
        Objects.requireNonNull(speaker, "speaker");
        return speaker.speak(answers);
    }
}
