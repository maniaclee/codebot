package com.lvbby.codebot.fsm.exception;

/**
 * Created by peng on 16/8/1.
 */
public class FsmBuildException extends RuntimeException {
    public FsmBuildException(String message) {
        super(message);
    }

    public static void check(boolean isTrue, String errorMessage) {
        if (!isTrue)
            throw new FsmBuildException(errorMessage);
    }
}
