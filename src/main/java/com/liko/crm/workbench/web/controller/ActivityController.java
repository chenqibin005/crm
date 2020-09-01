package com.liko.crm.workbench.web.controller;

import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.settings.service.impl.UserServiceImpl;
import com.liko.crm.utils.PrintJson;
import com.liko.crm.utils.ServiceFactory;
import com.liko.crm.workbench.service.ActivityService;
import com.liko.crm.workbench.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:20
 */
public class ActivityController extends HttpServlet {
    private ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/workbench/Activity/getUserList.do".equals(path)) {

            getUserList(request, response);
        } else if ("/workbench/Activity/xxx.do".equals(path)) {
            //xxx.do(request,response)
        }
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
