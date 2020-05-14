package com.funtl.myshop.plus.business.controller;

import com.funtl.myshop.plus.commons.dto.ResponseResult;
import com.funtl.myshop.plus.provider.api.UmsAdminService;
import com.funtl.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 * @author 邱豪辉
 */
@RestController
@RequestMapping("/reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /**
     * 注册
     * @param umsAdmin
     * @return
     */
    @PostMapping("/")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin){
        String msg = validateReg(umsAdmin);

        if(msg == null){
            int result = umsAdminService.insert(umsAdmin);
            UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());

            if(result > 0){
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(),"注册成功",admin);
            }
        }

        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(),msg != null ? msg : "注册失败");
    }

    /**
     * 注册信息验证
     * @param umsAdmin
     * @return
     */
    private String validateReg(UmsAdmin umsAdmin){
        UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());
        if(admin != null){
            return "用户名已存在,重新输入";
        }
        return null;
    }
}
