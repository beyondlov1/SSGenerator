package com.beyond.recognizer;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public interface Recognizer<T,S> {
    T parse(S s);
}
