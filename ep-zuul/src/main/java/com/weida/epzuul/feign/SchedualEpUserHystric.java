package com.weida.epzuul.feign;

import org.springframework.stereotype.Component;

@Component
public class SchedualEpUserHystric implements SchedualEpUser{
    @Override
    public String verification(String name) {
        return null;
    }
}
