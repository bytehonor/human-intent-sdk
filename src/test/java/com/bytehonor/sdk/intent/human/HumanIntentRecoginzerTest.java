package com.bytehonor.sdk.intent.human;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.worker.CacheIntentWorker;

public class HumanIntentRecoginzerTest {

    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentRecoginzerTest.class);

    @Test
    public void test() {
        HumanIntentRecoginzer recognizer = HumanIntentRecoginzer.create("测试");

        List<String> list = new ArrayList<String>();
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
        list.add("【收到不支持的消息类型，暂无法显示】");
        for (String text : list) {
            LOG.info("**** text:{}", text);
            IntentRequest request = IntentRequest.create(text, "testuser");
            IntentResult result = recognizer.recognize(request, new CacheIntentWorker());
            print(result);
        }
    }

    private void print(IntentResult result) {
        LOG.info("**** resolver:{}", result.getResolver());
        List<IntentAnswer> answers = result.getAnswers();
        for (IntentAnswer answer : answers) {
            LOG.info("{}", answer.getValue());
        }
    }

}
