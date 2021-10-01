package com.bytehonor.sdk.intent.human.exception;

/**
 * @author lijianqiang
 *
 */
public class HumanIntentSdkException extends RuntimeException {

    private static final long serialVersionUID = 8241747723232910227L;

    public HumanIntentSdkException() {
        super();
    }

    public HumanIntentSdkException(String message) {
        super(message);
    }

    public HumanIntentSdkException(Exception cause) {
        super(cause);
    }
}
