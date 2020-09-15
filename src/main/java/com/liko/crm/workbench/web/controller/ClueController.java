package com.liko.crm.workbench.web.controller;

import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.settings.service.impl.UserServiceImpl;
import com.liko.crm.utils.DateTimeUtil;
import com.liko.crm.utils.PrintJson;
import com.liko.crm.utils.ServiceFactory;
import com.liko.crm.utils.UUIDUtil;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.domain.Clue;
import com.liko.crm.workbench.service.ClueService;
import com.liko.crm.workbench.service.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        } else if ("/workbench/Clue/saveClue.do".equals(path)) {
            save(request, response);
        } else if ("/workbench/Clue/pageList.do".equals(path)) {
            pageList(request, response);
        }
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

       String pageNostr=request.getParameter("pageNo");
       int pageNo =Integer.valueOf(pageNostr);
       String pageSizestr=request.getParameter("pageSize");
       int pageSize = Integer.valueOf(pageSizestr);
       int skipPage =(pageNo-1)*pageSize;
       String company=request.getParameter("company");
       String fullname=request.getParameter("fullname");
       String mphone=request.getParameter("mphone");
       String owner=request.getParameter("owner");
       String phone=request.getParameter("phone");
       String source=request.getParameter("source");
       String state=request.getParameter("state");

        Map<String,Object> map =new HashMap<>();
        map.put("skipPage",skipPage);
        map.put("pageSize",pageSize);
        map.put("company",company);
        map.put("fullname",fullname);
        map.put("mphone",mphone);
        map.put("owner",owner);
        map.put("source",source);
        map.put("state",state);
        map.put("phone",phone);

        System.out.println(map);
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Pagination<Clue> page =cs.pageList(map);
        PrintJson.printJsonObj(response,page);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");
        String createBy =((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        Clue clue =new Clue();
        clue.setId(id);
        clue.setAddress(address);
        clue.setAppellation(appellation);
        clue.setCompany(company);
        clue.setContactSummary(contactSummary);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        clue.setDescription(description);
        clue.setEmail(email);
        clue.setFullname(fullname);
        clue.setJob(job);
        clue.setPhone(phone);
        clue.setOwner(owner);
        clue.setWebsite(website);
        clue.setState(state);
        clue.setMphone(mphone);
        clue.setSource(source);
        clue.setNextContactTime(nextContactTime);

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean success =cs.saveClue(clue);
        PrintJson.printJsonFlag(response,success);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response, userList);

    }
}
