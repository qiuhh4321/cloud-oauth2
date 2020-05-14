package com.funtl.myshop.plus.provider.service;

import com.funtl.myshop.plus.provider.api.UmsAdminService;
import com.funtl.myshop.plus.provider.domain.UmsAdmin;
import com.funtl.myshop.plus.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 * @author 邱豪辉
 */
@Service(version = "1.0.0")
public class UmsServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());

        if(umsAdmin.getStatus() == null){
            umsAdmin.setStatus(0);
        }

        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));


        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public UmsAdmin get(String username) {
        Example example = new Example(UmsAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public UmsAdmin get(UmsAdmin umsAdmin) {
        return umsAdminMapper.selectOne(umsAdmin);
    }

}
