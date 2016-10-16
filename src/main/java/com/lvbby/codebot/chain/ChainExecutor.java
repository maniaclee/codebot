package com.lvbby.codebot.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by lipeng on 16/8/1.
 */
public class ChainExecutor<T> {

    List<ContextHandlerHolder<T>> handlers = new ArrayList<>();
    private ExecutorService executorService;

    private ChainExecutor(List<ContextHandler<T>> handlers) {
        handlers.forEach(this::add);
    }


    public static <T> ChainExecutor<T> create(List<ContextHandler<T>> handlers) {
        return new ChainExecutor(handlers);
    }

    public ChainExecutor<T> add(List<ContextHandler<T>> handlers) {
        handlers.forEach(this::add);
        return this;
    }

    public ChainExecutor<T> addParallel(List<ContextHandler<T>> handlers) {
        handlers.forEach(h -> handlers.add(new ContextHandlerHolder<>(h).setParallel(true)));
        return this;
    }

    public ChainExecutor<T> add(ContextHandler<T> h) {
        if (h != null)
            handlers.add(new ContextHandlerHolder<>(h));
        return this;
    }

    public void exec(T t) {
        if (handlers == null) return;
        int size = handlers.size();
        for (int i = 0; i < size; i++) {
            ContextHandlerHolder<T> h = handlers.get(i);
            h.handle(t);
        }
    }

    private class ContextHandlerHolder<T> implements ContextHandler<T> {

        private ContextHandler<T> handler;
        private boolean parallel = false;

        public ContextHandlerHolder(ContextHandler<T> handler) {
            this.handler = handler;
        }

        @Override
        public void handle(T context) {
            handler.handle(context);
        }

        public boolean isParallel() {
            return parallel;
        }

        public ContextHandlerHolder<T> setParallel(boolean parallel) {
            this.parallel = parallel;
            return this;
        }
    }

}
