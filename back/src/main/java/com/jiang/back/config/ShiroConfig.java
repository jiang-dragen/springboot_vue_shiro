package com.jiang.back.config;

import com.jiang.back.Realm.MyRealm;
import com.jiang.back.shiro.JwtFilter;
import com.jiang.back.shiro.MyCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
@project name back
@author jiang
@time   2021-09-16-9:43

*/
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;
    @Autowired
    private MyCredentialsMatcher myCredentialsMatcher; //密码加密验证



    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        myRealm.setCredentialsMatcher(myCredentialsMatcher);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager , JwtFilter jwtFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //过滤
        Map<String , Filter> filterMap = new HashMap<>();
        filterMap.put("jwt" , jwtFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> map = new LinkedHashMap<>();
        //防止swagger被拦截
        map.put("/swagger-ui.html**" , "anon");
        map.put("/v2/api-docs" , "anon");
        map.put("/swagger-resources/**" , "anon");
        map.put("/web/jars" , "anon");

//        map.put("/user/test", "anon");//无需登录
//        map.put("/user/test2", "authc"); //需要登录验证 ==> 跳转到LoginUrl登录页面
        map.put("/**" , "jwt");// 自定义jwt拦截验证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/user/login");

        return shiroFilterFactoryBean;
    }

    @Bean
    public JwtFilter getJwtFilter() {
        return new JwtFilter();
    }

    /**
     * 开启注解功能
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
