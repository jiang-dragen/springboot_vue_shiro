package com.jiang.back.Realm;

import com.jiang.back.entity.Role;
import com.jiang.back.entity.User;
import com.jiang.back.service.IPermissionService;
import com.jiang.back.service.IRoleService;
import com.jiang.back.service.IUserRoleService;
import com.jiang.back.service.IUserService;
import com.jiang.back.token.JwtToken;
import com.jiang.back.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/*
@project name back
@author jiang
@time   2021-09-16-9:47

*/
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IPermissionService iPermissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.iterator().next();
        Set<String> roles = iRoleService.getRoleByUsername(username);
        Set<String> permission = iPermissionService.getPermissionByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permission);
        return info;
    }

    /**
     * 验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String jwt = (String)jwtToken.getPrincipal();
        //解析
        Claims claims = JwtUtil.parseJWT(jwt);
        String username = claims.getId();
        User user = iUserService.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}
