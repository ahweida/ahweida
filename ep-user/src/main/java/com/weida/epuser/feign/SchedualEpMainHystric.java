package com.weida.epuser.feign;

import org.springframework.stereotype.Component;

@Component
public class SchedualEpMainHystric implements SchedualEpMain {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
