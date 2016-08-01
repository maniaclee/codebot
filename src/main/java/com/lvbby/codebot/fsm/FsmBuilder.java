package com.lvbby.codebot.fsm;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lvbby.codebot.fsm.exception.FsmBuildException;

import java.util.List;

/**
 * Created by peng on 16/8/1.
 */
public class FsmBuilder<T, C> {
    Multimap<T, FsmBranch<T, C>> map = ArrayListMultimap.create();

    private T current;


    public FsmBuilder status(T t) {
        current = t;
        map.put(current, new FsmBranch<>());
        return this;
    }

    public FsmBuilder to(T t, FSMCondition<T, C> condition, List<StatusHandler<C>> handlers) {
        FsmBuildException.check(map.get(current).stream().anyMatch(tFsmBranch -> Objects.equal(tFsmBranch.getT(), t)), "duplicated dest status of " + t);
        FsmBranch<T, C> branch = new FsmBranch<>();
        branch.setT(t);
        branch.setCondition(condition == null ? FSMCondition.TRUE : condition);
        branch.setHandlers(handlers);
        map.put(current, branch);
        return this;
    }

    public List<StatusHandler<C>> getHandlers(T from, T to) {
        return map.get(from).stream()
                .filter(tcFsmBranch -> Objects.equal(tcFsmBranch.getT(), to))
                .findFirst().get().getHandlers();

    }


}
