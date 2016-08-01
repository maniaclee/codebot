package com.lvbby.codebot.fsm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by peng on 16/8/1.
 */
public class FsmBranch<T, C> {
    T t;
    FSMCondition<T> condition;
    List<StatusHandler<C>> handlers = new LinkedList<>();


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public FSMCondition<T> getCondition() {
        return condition;
    }

    public void setCondition(FSMCondition<T> condition) {
        this.condition = condition;
    }

    public List<StatusHandler<C>> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<StatusHandler<C>> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FsmBranch<?, ?> fsmBranch = (FsmBranch<?, ?>) o;

        return t != null ? t.equals(fsmBranch.t) : fsmBranch.t == null;

    }

    @Override
    public int hashCode() {
        return t != null ? t.hashCode() : 0;
    }
}
