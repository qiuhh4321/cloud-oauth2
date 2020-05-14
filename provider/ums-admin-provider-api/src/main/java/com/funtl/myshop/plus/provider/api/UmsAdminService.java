package com.funtl.myshop.plus.provider.api;

import com.funtl.myshop.plus.provider.domain.UmsAdmin;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 * @author 邱豪辉
 */
public interface UmsAdminService {

    /**
     * 新增用户
     * @param umsAdmin
     * @return
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 获取用户
     * @param username
     * @return
     */
    UmsAdmin get(String username);


    /**
     * 获取用户
     * @param umsAdmin
     * @return
     */
    UmsAdmin get(UmsAdmin umsAdmin);
}
