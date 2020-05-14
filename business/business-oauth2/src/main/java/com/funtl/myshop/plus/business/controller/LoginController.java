package com.funtl.myshop.plus.business.controller;

import com.funtl.myshop.plus.business.dto.LoginInfo;
import com.funtl.myshop.plus.business.dto.LoginParam;
import com.funtl.myshop.plus.business.feign.ProfileFeign;
import com.funtl.myshop.plus.business.service.UserDetailsServiceImpl;
import com.funtl.myshop.plus.commons.dto.ResponseResult;
import com.funtl.myshop.plus.commons.utils.MapperUtils;
import com.funtl.myshop.plus.commons.utils.OkHttpClientUtil;
import com.funtl.myshop.plus.provider.domain.UmsAdmin;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/12
 */
@RestController
public class LoginController {

    private static  final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    private static  final String GRANT_TYPE = "password";

    private static  final String CLIENT_ID = "client";

    private static  final String CLIENT_SECRET = "secret";

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private ProfileFeign profileFeign;

    @Autowired
    public TokenStore tokenStore;

    @PostMapping("/user/login")
    public ResponseResult<HashMap<String,Object>> login(@RequestBody LoginParam loginParam){
        HashMap<String,Object> result = Maps.newHashMap();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());

        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {

            return new ResponseResult<HashMap<String,Object>>(ResponseResult.CodeStatus.FAIL,"失败",null);
        }

        Map<String,String> paramMap = Maps.newHashMap();

        paramMap.put("username",loginParam.getUsername());
        paramMap.put("password",loginParam.getPassword());
        paramMap.put("grant_type",GRANT_TYPE);
        paramMap.put("client_id",CLIENT_ID);
        paramMap.put("client_secret",CLIENT_SECRET);
        try{
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN,paramMap);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String,Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token",token);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseResult<HashMap<String,Object>>(ResponseResult.CodeStatus.OK,"成功",result );
    }

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/info")
    public ResponseResult<LoginInfo> info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 封装并返回结果
        LoginInfo loginInfo = new LoginInfo();
        String jsonString = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString,"data",UmsAdmin.class);
        loginInfo.setName(umsAdmin.getUsername());
        loginInfo.setRoles(umsAdmin.getUsername());
        loginInfo.setAvatar(umsAdmin.getIcon());
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK, "获取用户信息", loginInfo);
    }

    @PostMapping("/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(auth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "用户注销", null);
    }
}
