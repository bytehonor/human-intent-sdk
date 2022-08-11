package com.bytehonor.sdk.intent.human.speak;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentAnswers;

public class SimpleAnswerSpeaker implements AnswerSpeak {

    @Override
    public String speak(IntentAnswers answers) {
        List<IntentAnswer> list = answers.getList();
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (IntentAnswer item : list) {
            sb.append(item.getValue());
            sb.append("\r\n");
        }
        return sb.toString();
    }

}
