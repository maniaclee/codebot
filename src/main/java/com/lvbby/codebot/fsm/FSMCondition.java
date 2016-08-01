package com.lvbby.codebot.fsm;

/**
 * Created by peng on 16/8/1.
 */
public interface FSMCondition<T, C> {
    FSMCondition TRUE = (o, context) -> true;

    boolean match(T t, C context);
}
