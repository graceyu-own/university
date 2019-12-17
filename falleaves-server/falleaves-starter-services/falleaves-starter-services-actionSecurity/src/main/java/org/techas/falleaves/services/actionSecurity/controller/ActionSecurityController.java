package org.techas.falleaves.services.actionSecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.utils.HttpCode;
import org.techas.falleaves.utils.ResponseData;

@RestController
public class ActionSecurityController {

    @RequestMapping("/checkAction")
    public ResponseData<String> checkAction() {

        return ResponseData.New(HttpCode.SUCCESS, null);
    }

}
