package com.lvbby.codebot.chain;

/**
 * Created by peng on 16/7/30.
 */
public class ContextTaskHandler<T, R> implements ContextHandler<T> {

    ContextTask<T, R> contextTask;

    public ContextTaskHandler(ContextTask<T, R> contextTask) {
        this.contextTask = contextTask;
    }

    @Override
    public void handle(T context) {
        contextTask.process(context);
    }

}
