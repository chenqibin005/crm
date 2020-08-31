package com.liko.crm.settings.web.controller;

import com.liko.crm.settings.dao.UserDao;
import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.settings.service.impl.UserServiceImpl;
import com.liko.crm.utils.MD5Util;
import com.liko.crm.utils.PrintJson;
import com.liko.crm.utils.ServiceFactory;
import com.liko.crm.utils.SqlSessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/8/31 11:25
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/settings/User/login.do".equals(path)) {

            login(request, response);
        } else if ("/settings/User/xxx.do".equals(path)) {
            //xxx.do(request,response)
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        String ip =request.getRemoteAddr();
        loginPwd = MD5Util.getMD5(loginPwd);

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {
            User user = us.login(loginAct, loginPwd, ip);
            //登录成功
            PrintJson.printJsonFlag(response,true);
            request.getSession().setAttribute("user", user);
        }catch (Exception e){
            e.printStackTrace();
            //登录验证失败返回错误信息
            Map<String,Object> map= new HashMap<>();
            map.put("success",false);
            map.put("msg",e.getMessage());
            PrintJson.printJsonObj(response,map);
        }
    }

}
