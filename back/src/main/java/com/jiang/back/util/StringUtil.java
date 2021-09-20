package com.jiang.back.util;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/*
@project name back
@author jiang
@time   2021-09-16-12:23

*/
public class StringUtil {
    /**
     * 生成随机字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密
     */
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static Integer changeString(String string) {
        if (string != null && !StringUtils.isBlank(string)) {
            return Integer.valueOf(string);
        } else {
            return null;
        }
    }

}
