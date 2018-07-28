package com.hzgc.common.service.journal.helper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author liuzhikun
 * @date 2018/05/28
 */
public class PrincipalHolder {
    public static String getCurrentUsername() {
        String user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            user = ((User) principal).getUsername();
        } else if (principal instanceof String) {
            user = (String) principal;
        }

        return user;
    }
}
