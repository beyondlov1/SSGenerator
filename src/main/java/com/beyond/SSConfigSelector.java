package com.beyond;

import com.beyond.entity.ResponseEntity;
import com.beyond.entity.SSConfigEntity;

public interface SSConfigSelector<T> {
    SSConfigEntity selectFrom(ResponseEntity<T> responseEntity);
}
