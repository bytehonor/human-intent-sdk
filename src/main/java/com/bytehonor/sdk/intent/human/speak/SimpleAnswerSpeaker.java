package com.bytehonor.sdk.intent.human.speak;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.intent.human.model.IntentOutput;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;

public class SimpleAnswerSpeaker implements AnswerSpeak {

    @Override
    public String speak(IntentAnswer answer) {
        List<IntentOutput> list = answer.getList();
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (IntentOutput item : list) {
            sb.append(item.getValue());
            sb.append("\r\n");
        }
        return sb.toString();
    }

}
