package com.lvbby.codebot.fsm;

import com.google.common.base.Objects;
import com.lvbby.codebot.fsm.exception.FSMException;
import com.lvbby.codebot.fsm.exception.FsmBuildException;

import java.util.List;

/**
 * Created by peng on 16/8/1.
 */
public class FsmExecutor<T, C> {
    private FsmBuilder<T, C> builder;

    public boolean canReach(T from, T to, C context) {
        return builder.map.get(from).stream().anyMatch(b -> Objects.equal(b.getT(), to) && b.getCondition().match(to, context));
    }

    public void transit(T from, List<T> tos, C c) {
        for (T to : tos) {
            FsmBuildException.check(canReach(from, to, c), "");
            for (StatusHandler<C> handler : builder.getHandlers(from, to)) {
                try {
                    handler.handle(c);
                } catch (FSMException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
