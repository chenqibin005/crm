package com.liko.crm.web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hangzhi1063
 * @date 2020/9/1 14:02
 */
public class LoginFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain Chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session =request.getSession();

        String path = request.getServletPath();
        System.out.println(path);
        if ("/login.jsp".equals(path)||"/settings/User/login.do".equals(path)){
            Chain.doFilter(req,resp);
        }else {
            if (session.getAttribute("user") != null) {
                Chain.doFilter(req, resp);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
