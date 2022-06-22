package com.bytehonor.sdk.intent.human.exception;

/**
 * @author lijianqiang
 *
 */
public class HumanIntentException extends RuntimeException {

    private static final long serialVersionUID = 8241747723232910227L;

    public HumanIntentException() {
        super();
    }

    public HumanIntentException(String message) {
        super(message);
    }

    public HumanIntentException(Exception cause) {
        super(cause);
    }
}
