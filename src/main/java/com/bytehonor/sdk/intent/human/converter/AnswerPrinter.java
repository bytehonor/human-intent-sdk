package com.bytehonor.sdk.intent.human.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.intent.human.model.IntentAnswers;

public class AnswerPrinter {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerPrinter.class);

    public static void print(IntentAnswers answers) {
        print(answers, new SimpleAnswerConverter());
    }

    public static void print(IntentAnswers answers, AnswerConverter formatter) {
        String text = formatter.convert(answers);
        LOG.info("{}", text);
    }
}
