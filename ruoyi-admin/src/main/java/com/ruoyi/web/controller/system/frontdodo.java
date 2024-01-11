package com.ruoyi.web.controller.system;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.impl.lptest;




@RestController
public class frontdodo {
    
    @Autowired
    private lptest lipancstest;




    @GetMapping("/lipancs123")
    public AjaxResult lipancs123()
    {
        lipancstest.dodo();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("name","ok");
        return ajax;

    }

}
