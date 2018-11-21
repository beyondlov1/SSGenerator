package com.beyond.race;

import java.util.concurrent.Callable;

/**
 * @author beyondlov1
 * @date 2018/11/20
 */
public interface Rule<T> {
    Object execute(T t);
}
