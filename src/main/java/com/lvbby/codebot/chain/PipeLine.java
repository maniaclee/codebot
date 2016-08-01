package com.lvbby.codebot.chain;

/**
 * Created by peng on 16/7/27.
 */
public interface PipeLine<A, T> {
    void handle(A t, T context);
}
