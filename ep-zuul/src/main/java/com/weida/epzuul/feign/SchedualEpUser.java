package com.weida.epzuul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ep-user", fallback = SchedualEpUserHystric.class)
public interface SchedualEpUser {

    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    String verification(@RequestParam(value = "name") String name);
}
