package com.funtl.myshop.plus.provider.service;

import com.funtl.myshop.plus.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 * @author 邱豪辉
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        return "Echo Hello Dubbo " + string;
    }
}
