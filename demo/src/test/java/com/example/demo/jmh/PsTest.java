package com.example.demo.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Benchmark;

public class PsTest {



    //JMH压测工具测试使用
    @Benchmark
    //以下声明为附加场景，一般直接Benchmark就可以
    @Warmup(iterations=1,time=3)//在专业测试里面首先要进行预热，预热多少次，多少时间
    @Fork(5)//意思是用多少个线程去执行我们的程序
    @BenchmarkMode(Mode.Throughput)//是对基准测试的一个模式，这个模式用的最多的是
//     Throughput	//吞吐量
    @Measurement(iterations=1,time=3)//是整个测试要测试多少遍，调用这个方法要调用多少次
    public void testet() {
        PS.foreach();
    }

}
