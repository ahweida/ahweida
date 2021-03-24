package com.weida.epmain.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ep-user", fallback = SchedualEpUserHystric.class)
public interface SchedualEpUser {

    @GetMapping(value = "/userProject/getUserProjects")
    String getUserProjects(@RequestParam(value = "userId") Integer userId);
}
