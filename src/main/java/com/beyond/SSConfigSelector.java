package com.beyond;

import com.beyond.entity.ResponseEntity;
import com.beyond.entity.SSConfigEntity;

import java.util.List;

public interface SSConfigSelector<T> {
    SSConfigEntity selectFrom(ResponseEntity<T> responseEntity);
    List<SSConfigEntity> selectAll(ResponseEntity<T> responseEntity);
}
