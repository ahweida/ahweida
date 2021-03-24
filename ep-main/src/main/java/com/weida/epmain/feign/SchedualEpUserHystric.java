package com.weida.epmain.feign;

import org.springframework.stereotype.Component;

@Component
public class SchedualEpUserHystric implements SchedualEpUser{

    @Override
    public String getUserProjects(Integer userId) {
        return "sorry "+ userId;
    }
}
