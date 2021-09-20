package com.jiang.back.shiro;

import com.jiang.back.entity.User;
import com.jiang.back.service.IUserService;
import com.jiang.back.token.JwtToken;
import com.jiang.back.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
@project name back
@author jiang
@time   2021-09-16-12:27

*/
@Component
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private IUserService iUserService;
    /**
     * 用于验证的方法
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken) token;
        if (jwtToken.getPassword() == null){
            return true;
        }
        String inPassword = new String(jwtToken.getPassword());
        String username = String.valueOf(info.getPrincipals());
        String dbPassword = (String) info.getCredentials();
        User user = iUserService.getUserByUsername(username);
        String salt = user.getSalt();

        return this.equals(StringUtil.md5(inPassword + salt) , dbPassword);
    }
}
