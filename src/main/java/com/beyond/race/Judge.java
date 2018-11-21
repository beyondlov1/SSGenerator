package com.beyond.race;

import java.util.List;
import java.util.concurrent.Callable;

public interface Judge {
    Callable getFirst(List<Callable> runnerList);
}
