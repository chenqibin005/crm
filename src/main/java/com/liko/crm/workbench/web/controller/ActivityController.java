package com.liko.crm.workbench.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:20
 */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/settings/User/login.do".equals(path)) {

            //xxx.do(request, response);
        } else if ("/settings/User/xxx.do".equals(path)) {
            //xxx.do(request,response)
        }
    }
}
