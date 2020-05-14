package com.funtl.myshop.plus.business.feign;

import com.funtl.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/13
 * @author 邱豪辉
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {

    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

}
