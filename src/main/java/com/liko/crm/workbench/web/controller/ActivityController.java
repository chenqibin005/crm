package com.liko.crm.workbench.web.controller;

import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.settings.service.impl.UserServiceImpl;
import com.liko.crm.utils.DateTimeUtil;
import com.liko.crm.utils.PrintJson;
import com.liko.crm.utils.ServiceFactory;
import com.liko.crm.utils.UUIDUtil;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.domain.Activity;
import com.liko.crm.workbench.domain.ActivityRemark;
import com.liko.crm.workbench.service.ActivityService;
import com.liko.crm.workbench.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:20
 */
public class ActivityController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/workbench/Activity/getUserList.do".equals(path)) {

            getUserList(request, response);
        } else if ("/workbench/Activity/save.do".equals(path)) {
            save(request, response);
        } else if ("/workbench/Activity/pageList.do".equals(path)) {
            pageList(request, response);
        } else if ("/workbench/Activity/delete.do".equals(path)) {
            delete(request, response);
        } else if ("/workbench/Activity/selectUlistAndActivity.do".equals(path)) {
            edit(request, response);
        } else if ("/workbench/Activity/update.do".equals(path)) {
            update(request, response);
        } else if ("/workbench/Activity/detail.do".equals(path)) {
            detail(request, response);
        } else if ("/workbench/Activity/getRemarkByAid.do".equals(path)) {
            getRemarkByAid(request, response);
        } else if ("/workbench/Activity/delRemark.do".equals(path)) {
            delRemark(request, response);
        } else if ("/workbench/Activity/saveRemark.do".equals(path)) {
            saveRemark(request, response);
        }else if ("/workbench/Activity/UpdateRemark.do".equals(path)) {
            UpdateRemark(request, response);
        }
    }

    private void UpdateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入UpdateRemark");
        String noteContent = request.getParameter("noteContent");
        String id =request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        ActivityRemark ar =new ActivityRemark();

        ar.setNoteContent(noteContent);
        ar.setId(id);
        ar.setEditTime(editTime);
        ar.setEditBy(editBy);
        ar.setEditFlag("1");
        Map<String,Object> map =new HashMap<>();


        boolean flag =as.UpdateRemark(ar);
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);

    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        String noteContent = request.getParameter("noteContent");
        String activityId = request.getParameter("activityId");
        String id =UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        ActivityRemark ar =new ActivityRemark();
        ar.setActivityId(activityId);
        ar.setNoteContent(noteContent);
        ar.setId(id);
        ar.setCreateTime(createTime);
        ar.setCreateBy(createBy);
        ar.setEditFlag("0");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean b= as.saveRemark(ar);
        Map<String,Object> map =new HashMap<>();
        map.put("success",b);
        map.put("ar",ar);
        System.out.println(map);
        PrintJson.printJsonObj(response,map);

    }

    private void delRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean success = as.delRemark(id);
        PrintJson.printJsonFlag(response, success);
    }

    private void getRemarkByAid(HttpServletRequest request, HttpServletResponse response) {
        String aid = request.getParameter("aid");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> ar = as.getRemarkByAid(aid);
        System.out.println(ar);
        PrintJson.printJsonObj(response, ar);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a = as.detail(id);
        request.setAttribute("a", a);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);
    }


    private void edit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");


        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Map<String, Object> map = as.selectUlistAndActivity(id);
        //List<User> ulist=us.getUserList();
        //as.editActivity(id);
        PrintJson.printJsonObj(response, map);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        //获取ids
        String[] ids = request.getParameterValues("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.delect(ids);
        PrintJson.printJsonFlag(response, flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //略过的页数 当前页减一 x 每页显示的个数
        int skipPage = (pageNo - 1) * pageSize;
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("skipPage", skipPage);
        map.put("pageSize", pageSize);
        //通过as调用pageList方法 返回一个List<Activity>;
        //考虑到分页查询的高重复性 新建一个vo类存放
        Pagination<Activity> vo = as.pageList(map);
        PrintJson.printJsonObj(response, vo);


    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User) request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editBy);
        boolean flag = as.update(a);
        PrintJson.printJsonFlag(response, flag);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);
        boolean flag = as.save(a);
        PrintJson.printJsonFlag(response, flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response, userList);
    }
}
