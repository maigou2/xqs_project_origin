package com.xqs.shopservice.controller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.shopservice.service.WebSiteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webSite")
public class WebSiteController {

    @Resource
    private WebSiteService webSiteService;

    @RequestMapping("/addCommodity")
    public BaseResponseDTO addCommodity() {
        return BaseResponseDTO.success();
    }


}
