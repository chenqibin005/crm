package com.liko.crm.workbench.web.controller;

import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.settings.service.impl.UserServiceImpl;
import com.liko.crm.utils.PrintJson;
import com.liko.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:36
 */
public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/workbench/Clue/getUserList.do".equals(path)) {

             getUserList(request, response);
        } else if ("/workbench/Clue/xxx.do".equals(path)) {
            // save(request, response);
        }
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList=us.getUserList();
        PrintJson.printJsonObj(response,userList);

    }
}
