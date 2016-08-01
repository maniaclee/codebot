package com.lvbby.codebot.fsm.exception;

/**
 * Created by peng on 16/8/1.
 */
public class FSMException extends RuntimeException {
    public FSMException(String message) {
        super(message);
    }

    public static void check(boolean isTrue, String errorMessage) {
        if (!isTrue)
            throw new FSMException(errorMessage);
    }
}
