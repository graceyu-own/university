package org.techas.falleaves.services.manager.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techas.falleaves.security.service.ISecurityService;
import org.techas.falleaves.services.manager.services.IManagerUserService;
import org.techas.falleaves.utils.HttpCode;
import org.techas.falleaves.utils.ResponseData;
import org.techas.falleaves.utils.UserStatusType;
import org.techas.falleaves.utils.Utils;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class ManagerUserController {

    @Resource
    private ISecurityService securityService;

    @Resource
    private IManagerUserService managerUserService;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseData<String> delUser(
        @PathVariable String id
    ) {
        // check url中的id是否为空, (显然不可能为空, 否则无法访问)
         if("".equals(id)) {
             return ResponseData.New(HttpCode.MANAGER_USER__USER_ID_EMPTY, "User id cannot be empty");
         }

         // check url中的id是不是一个合法的id, 也有可能是字符串, 因此需要转long测试
         Long val = Utils.toLong(id);
         if(null == val) {
             return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
         }

         // check 是否存在此用户id, 因为使用jpa save, 如果不存在的话, 会create
         if(!securityService.hasUserById(val)) {
            return ResponseData.New(HttpCode.MANAGER_USER__USER_ID_NOT_EXISTS, "User id not exists");
         }

         // 尝试删除此用户, 保护但不限于user/userLogin/userInfo
         if(!managerUserService.delUser(val)){
             return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
         }

         return ResponseData.New(HttpCode.COMMON__SUCCESS, "Delete user success.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData<String> updateUser(
        @PathVariable String id,
        String status
    ) {

        if("".equals(id)) {
            return ResponseData.New(HttpCode.MANAGER_USER__USER_ID_EMPTY, "User id cannot be empty");
        }

        if("".equals(status)) {
            return ResponseData.New(HttpCode.MANAGER_USER__STATUS_EMPTY, "User status cannot be empty.");
        }

        Long userId = Utils.toLong(id);
        if(null == userId) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        Integer statusId = Utils.toInteger(status);
        if(null == statusId || !UserStatusType.has(statusId)) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        if(!securityService.hasUserById(userId)) {
            return ResponseData.New(HttpCode.MANAGER_USER__USER_ID_NOT_EXISTS, "User id not exists");
        }

        if(!managerUserService.updateUser(userId, statusId)) {
            return ResponseData.New(HttpCode.COMMON__INTERNET_SERVER_ERROR, "Unknown error.");
        }

        return ResponseData.New(HttpCode.COMMON__SUCCESS, "Update user success.");
    }

}
