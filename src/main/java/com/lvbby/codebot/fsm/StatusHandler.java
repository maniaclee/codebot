package com.lvbby.codebot.fsm;

import com.lvbby.codebot.fsm.exception.FSMException;

/**
 * Created by peng on 16/8/1.
 */
public interface StatusHandler<T> {
    void handle(T t) throws FSMException;
}
