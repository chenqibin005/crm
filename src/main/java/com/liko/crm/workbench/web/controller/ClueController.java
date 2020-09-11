package com.liko.crm.workbench.web.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:36
 */
public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/workbench/Clue/xxx.do".equals(path)) {

            // getUserList(request, response);
        } else if ("/workbench/Clue/xxx.do".equals(path)) {
            // save(request, response);
        }
    }
}
