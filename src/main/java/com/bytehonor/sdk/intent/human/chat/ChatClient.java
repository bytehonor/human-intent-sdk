package com.bytehonor.sdk.intent.human.chat;

import java.util.List;

import com.bytehonor.sdk.api.tuling.TulingApiClient;
import com.bytehonor.sdk.lang.spring.core.Randomizer;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringRemoveUtils;
import com.google.common.collect.Lists;

public class ChatClient {

    private static final List<String> LIST = Lists.newArrayList("呵", "嗯", "哦", "咦");

    public static String ask(String query, String user) {
        String clear = StringRemoveUtils.removeNonChinese(query);
        if (SpringString.isEmpty(clear)) {
            return ok();
        }
        return TulingApiClient.simpleAsk(query, user);
    }

    public static String ok() {
        int index = Randomizer.integer(0, 100) % LIST.size();
        return LIST.get(index);
    }
}
