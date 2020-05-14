package com.funtl.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/12
 */
@Data
public class LoginParam implements Serializable {

    private String username;
    private String password;
}
