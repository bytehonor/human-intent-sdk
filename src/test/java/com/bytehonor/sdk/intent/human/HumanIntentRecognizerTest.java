package com.bytehonor.sdk.intent.human;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.intent.human.speak.AnswerSpeaker;

public class HumanIntentRecognizerTest {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentRecognizerTest.class);

    @Test
    public void test() {
        HumanIntentRecognizer recognizer = HumanIntentRecognizer.builder().name("字节荣耀").build();
        String uuid = "62eea88d201601bfc592209554e57964";

        List<String> list = new ArrayList<String>();
        list.add("【收到不支持的消息类型，暂无法显示】");
        list.add("你是谁");
        list.add("你叫什么");
        list.add("你的名字");
        list.add("你会什么");
        list.add("你能做什么");
        list.add("你有什么功能");
        list.add("你能做什么你是谁");
        list.add("放歌");
        list.add("播放音乐");
        list.add("你多大");
        list.add("晚上吃什么");
        list.add("你会玩王者荣耀吗");
        list.add("周末去哪玩");
        for (String text : list) {
            LOG.info("**** text:{}", text);
            IntentRequest request = IntentRequest.create(text, uuid);
            IntentResult result = recognizer.recognize(request);
            print(result);
        }
    }

    private void print(IntentResult result) {
        IntentSession session = result.getSession();
        LOG.info("**** resolver:{}, {}, {}, {}", result.getResolver(), session.getId(), session.getNowIntent(),
                session.getPreIntent());
        LOG.info("speech:{}", AnswerSpeaker.speak(result.getAnswer()));
    }

}
