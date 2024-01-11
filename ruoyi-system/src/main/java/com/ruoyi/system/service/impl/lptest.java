package com.ruoyi.system.service.impl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysUserService;

@Service
public class lptest {
    @Autowired
    private ISysUserService userService;
    @PreAuthorize("@ss.hasPermi('system:user:resetUserTcode')")
    public String dodo()
    {
        System.out.println("qianduanSuccess!");
        return null;
        // String username=SecurityUtils.getUsername();
        // System.out.println(username);
        // userService.resetUserTcode(username,null);
        // return null;
    }

}
