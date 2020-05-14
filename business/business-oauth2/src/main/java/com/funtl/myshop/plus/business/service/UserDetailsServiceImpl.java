package com.funtl.myshop.plus.business.service;

import com.funtl.myshop.plus.provider.api.UmsAdminService;
import com.funtl.myshop.plus.provider.domain.UmsAdmin;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0",timeout = 5000)
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority("SystemContent");
        grantedAuthorities.add(grantedAuthority);
        grantedAuthorities.add(grantedAuthority1);
        UmsAdmin umsAdmin = umsAdminService.get(s);

        if(umsAdmin!=null){
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }

        return null;

    }
}
