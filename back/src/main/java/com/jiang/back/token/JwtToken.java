package com.jiang.back.token;

import com.jiang.back.util.JwtUtil;
import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.springframework.stereotype.Component;

/*
@project name back
@author jiang
@time   2021-09-16-11:58
自定义jwt Token
*/
@Data
@Component
public class JwtToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
//Class doesn't contain matching constructor for autowiring错误时==>类需要空参和带参构造器
    private String token;
    private char[] password;
    private boolean rememberMe = false;
    private String host;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public JwtToken() {
        this.rememberMe = false;
    }

    public JwtToken(String token, char[] password) {
        this(token, (char[]) password, false, (String) null);
    }

    public JwtToken(String token, String password) {
        this(token, (char[]) (password != null ? password.toCharArray() : null), false, (String) null);
    }

    public JwtToken(String token, char[] password, String host) {
        this(token, password, false, host);
    }

    public JwtToken(String token, String password, String host) {
        this(token, password != null ? password.toCharArray() : null, false, host);
    }

    public JwtToken(String token, char[] password, boolean rememberMe) {
        this(token, (char[]) password, rememberMe, (String) null);
    }

    public JwtToken(String token, String password, boolean rememberMe) {
        this(token, (char[]) (password != null ? password.toCharArray() : null), rememberMe, (String) null);
    }

    public JwtToken(String token, char[] password, boolean rememberMe, String host) {
        this.rememberMe = false;
        this.token = token;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public JwtToken(String token, String password, boolean rememberMe, String host) {
        this(token, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" - ");
        sb.append(JwtUtil.parseJWT(this.token).getId());
        sb.append(", rememberMe=").append(this.rememberMe);
        if (this.host != null) {
            sb.append(" (").append(this.host).append(")");
        }
        return sb.toString();
    }

}
