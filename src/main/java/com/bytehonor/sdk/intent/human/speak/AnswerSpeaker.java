package com.bytehonor.sdk.intent.human.speak;

import com.bytehonor.sdk.intent.human.model.IntentAnswers;

public class AnswerSpeaker {

    public static String speak(IntentAnswers answers) {
        return speak(answers, new SimpleAnswerSpeaker());
    }

    public static String speak(IntentAnswers answers, AnswerSpeak speaker) {
        return speaker.speak(answers);
    }
}
