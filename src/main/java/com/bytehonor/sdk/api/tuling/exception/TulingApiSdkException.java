package com.bytehonor.sdk.api.tuling.exception;

/**
 * @author lijianqiang
 *
 */
public class TulingApiSdkException extends RuntimeException {

    private static final long serialVersionUID = 8241747723232910227L;

    public TulingApiSdkException() {
        super();
    }

    public TulingApiSdkException(String message) {
        super(message);
    }

    public TulingApiSdkException(Exception cause) {
        super(cause);
    }
}
