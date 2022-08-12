package com.bytehonor.sdk.intent.human.chat;

import java.util.List;

import com.bytehonor.sdk.api.tuling.TulingApiClient;
import com.bytehonor.sdk.lang.spring.core.Randomizer;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringRemoveUtils;
import com.google.common.collect.Lists;

public class ChatClient {

    private static final List<String> LIST = Lists.newArrayList("呵", "嗯", "哦", "咦");

    private static final String DEF = "不知道怎么回答你了";

    public static String ask(String query, String user) {
        String clear = StringRemoveUtils.removeNonChinese(query);
        if (SpringString.isEmpty(clear)) {
            return ok();
        }
        String answer = TulingApiClient.simpleAsk(query, user).trim();
        if (SpringString.isEmpty(answer) == false) {
            return answer;
        }
        return DEF;
    }

    public static String ok() {
        int index = Randomizer.integer(0, 100) % LIST.size();
        return LIST.get(index);
    }
}
