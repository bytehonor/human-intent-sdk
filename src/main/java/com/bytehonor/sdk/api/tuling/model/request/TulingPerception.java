package com.bytehonor.sdk.api.tuling.model.request;

public class TulingPerception {

    private TulingInputText inputText;

    private TulingInputImage inputImage;

    private TulingSelfInfo selfInfo;

    public TulingInputText getInputText() {
        return inputText;
    }

    public void setInputText(TulingInputText inputText) {
        this.inputText = inputText;
    }

    public TulingInputImage getInputImage() {
        return inputImage;
    }

    public void setInputImage(TulingInputImage inputImage) {
        this.inputImage = inputImage;
    }

    public TulingSelfInfo getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(TulingSelfInfo selfInfo) {
        this.selfInfo = selfInfo;
    }

}
