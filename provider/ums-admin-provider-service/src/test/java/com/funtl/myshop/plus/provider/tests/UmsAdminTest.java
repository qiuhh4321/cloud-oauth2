package com.funtl.myshop.plus.provider.tests;

import com.funtl.myshop.plus.provider.api.UmsAdminService;
import com.funtl.myshop.plus.provider.domain.UmsAdmin;
import com.funtl.myshop.plus.provider.mapper.UmsAdminMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminTest {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void selectAll(){
        List<UmsAdmin> lists = umsAdminMapper.selectAll();
        lists.forEach(list -> {
            System.out.println(list.toString());
        });
    }

    @Test
    public void insert(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("Lusifer");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        umsAdmin.setIcon("");
        umsAdmin.setEmail("");
        umsAdmin.setNickName("");
        umsAdmin.setNote("");
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setStatus(0);

        int result = umsAdminService.insert(umsAdmin);
        Assert.assertEquals(result, 1);
    }
}
