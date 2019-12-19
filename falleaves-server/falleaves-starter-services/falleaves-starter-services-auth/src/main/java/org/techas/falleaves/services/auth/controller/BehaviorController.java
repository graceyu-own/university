package org.techas.falleaves.services.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.services.auth.service.IBehaviorService;
import org.techas.falleaves.utils.HttpCode;
import org.techas.falleaves.utils.ResponseData;

@RestController
public class BehaviorController {

    @Autowired
    private IBehaviorService behaviorAuthService;

    @RequestMapping("/checkBehavior")
    public ResponseData<String> checkBehavior() {

        if(!this.behaviorAuthService.commonBehavior()){
            return ResponseData.New(HttpCode.BEHAVIOR_VALID__COMMON_FAILED, null);
        }

        return ResponseData.New(HttpCode.COMMON__SUCCESS, "success");
    }

}
