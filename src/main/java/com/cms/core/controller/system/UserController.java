/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2017 © yangxiaobing, 873559947@qq.com
 *
 * This file is part of contentManagerSystem.
 * contentManagerSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * contentManagerSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with contentManagerSystem.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 这个文件是contentManagerSystem的一部分。
 * 您可以单独使用或分发这个文件，但请不要移除这个头部声明信息.
 * contentManagerSystem是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
 * 新的任何修改后的重新发布版必须同样在遵守GPL3或更后续的版本协议下发布.
 * 关于GPL协议的细则请参考COPYING文件，
 * 您可以在contentManagerSystem的相关目录中获得GPL协议的副本，
 * 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
 *
 * - Author: yangxiaobing
 * - Contact: 873559947@qq.com
 * - License: GNU Lesser General Public License (GPL)
 * - source code availability: http://git.oschina.net/yangxiaobing_175/contentManagerSystem
 */
package com.cms.core.controller.system;


import com.cms.constant.BussinessCode;
import com.cms.core.controller.BasicController;
import com.cms.core.domain.bo.BussinessMsg;
import com.cms.core.domain.vo.User;
import com.cms.core.service.UserService;
import com.cms.utils.BussinessMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户管理Controller
 *
 * @author yangxiaobing
 * @date 2017/7/6
 */
@Controller
@RequestMapping("user")
public class UserController extends BasicController {


    @Autowired
    private UserService userService;


    /**
     *跳转到用户列表页面
     * @return
     */
    @RequestMapping("/user_list")
    public String toUserListPage() {
        return "system/user_list";
    }
    /**
     * 加载用户列表List
     * @param user
     * @return
     */
    @RequestMapping("/ajax_user_list")
    @ResponseBody
    public String ajaxUserList(User user){
        return userService.selectUserResultPageList(user);
    }

    /**
     * 跳转到用户新增页面
     * @return
     */
    @RequestMapping("/user_add")
    public String toUserAddPage(Model model) {
        //新增页面标识
        model.addAttribute("pageFlag", "addPage");
        return "system/user_edit";
    }

    /**
     * 跳转到用户修改页面
     * @param userId 用户Id
     * @return
     */
    @RequestMapping("/user_update")
    public String userUpdatePage(Model model, Integer userId){
        User user = userService.selectUserById(userId);
        //修改页面标识
        model.addAttribute("pageFlag", "updatePage");
        model.addAttribute("user", user);
        return "system/user_edit";
    }


    /**
     * 跳转到用户角色分配页面
     * @param userId 用户Id
     * @return
     */
    @RequestMapping("/user_grant")
    public String userGrantPage(Model model, Integer userId){
//        User user = userService.selectUserRolesByUserId(userId);
//        model.addAttribute("user", user);
        return "system/user_grant";
    }



    /**
     * 保存用户信息
     * @param user 用户实体
     * @return
     */
    @RequestMapping("/ajax_save_user")
    @ResponseBody
    public BussinessMsg ajaxSaveUser(User user){
        try {
            return userService.saveOrUpdateUser(user, this.getCurrentLoginName());
        } catch (Exception e) {
            log.error("保存用户信息方法内部错误",e);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.USER_SAVE_ERROR);
        }
    }






}
