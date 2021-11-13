package com.example.demo.designModel.testCodw;


import org.springframework.cache.annotation.CacheEvict;

public class CacheEviTest {

    @CacheEvict(cacheResolver = "laia")
    public String ssss(){
        String sss= "老啊";
        return sss;
    }
}
