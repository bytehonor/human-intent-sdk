package com.bytehonor.sdk.intent.human.speak;

import com.bytehonor.sdk.intent.human.model.IntentAnswers;

public class AnswerSpeaker {

    public static String speech(IntentAnswers answers) {
        return speech(answers, new SimpleAnswerSpeaker());
    }

    public static String speech(IntentAnswers answers, AnswerSpeak formatter) {
        return formatter.speak(answers);
    }
}
