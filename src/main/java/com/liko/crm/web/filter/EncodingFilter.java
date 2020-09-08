package com.liko.crm.web.filter;
import javax.servlet.*;
import java.io.IOException;

/**
 * @author hangzhi1063
 * @date 2020/8/31 16:11
 */
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入过滤器");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }
}
