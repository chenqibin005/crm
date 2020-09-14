package com.liko.crm.web.listener; /**
 * @author hangzhi1063
 * @date 2020/9/14 14:00
 */

import com.liko.crm.settings.domain.DicValue;
import com.liko.crm.settings.service.DicTypeService;
import com.liko.crm.settings.service.DicValueService;
import com.liko.crm.settings.service.impl.DicValueServiceImpl;
import com.liko.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Listener implements ServletContextListener{



    public void contextInitialized(ServletContextEvent event) {
        System.out.println("上下文对象创建");
        ServletContext application=event.getServletContext();
        DicValueService dicValueService = (DicValueService) ServiceFactory.getService(new DicValueServiceImpl());
        Map<String, List<DicValue>> map=dicValueService.getAll();
        Set<String> set =map.keySet();
        for (String code:set){
            application.setAttribute(code,map.get(code));
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("上下文对象关闭");
    }



}
