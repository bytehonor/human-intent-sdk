package com.bytehonor.sdk.intent.human.model;

public class IntentResult {

    private IntentTarget target;

    private IntentAnswer answer;

    public static IntentResult non(IntentTarget target) {
        IntentResult result = new IntentResult();
        result.setTarget(target);
        result.setAnswer(IntentAnswer.non());
        return result;
    }

    public static IntentResult text(IntentTarget target, String answer) {
        IntentResult result = new IntentResult();
        result.setTarget(target);
        result.setAnswer(IntentAnswer.text(answer));
        return result;
    }

    public static IntentResult image(IntentTarget target, String answer) {
        IntentResult result = new IntentResult();
        result.setTarget(target);
        result.setAnswer(IntentAnswer.image(answer));
        return result;
    }

    public IntentTarget getTarget() {
        return target;
    }

    public void setTarget(IntentTarget target) {
        this.target = target;
    }

    public IntentAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(IntentAnswer answer) {
        this.answer = answer;
    }

}
