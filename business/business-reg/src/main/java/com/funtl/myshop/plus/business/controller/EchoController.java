package com.funtl.myshop.plus.business.controller;

import com.funtl.myshop.plus.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 */
@RestController
@RequestMapping(value = "echo")
public class EchoController {

    @Reference(version = "1.0.0")
    private EchoService echoService;

    @GetMapping("/{string}")
    public String echo(@PathVariable String string){
        return echoService.echo(string);
    }
}
