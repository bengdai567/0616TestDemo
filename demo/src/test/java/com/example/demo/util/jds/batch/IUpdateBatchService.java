package com.example.demo.util.jds.batch;

import java.util.List;

public interface IUpdateBatchService<T> {
    long doBatch(T var1, List var2);

    long doBatch(List<T> var1);
}
