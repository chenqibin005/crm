package com.liko.crm.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;

/**
 * @author hangzhi1063
 * @date 2020/8/25 13:35
 */
public class Mybatis {
   private static SqlSessionFactory factory= null;
    static {
        String config ="mybatis-config.xml";
        try {
            InputStream in = Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
        }catch (Exception e){
                e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession =null;
        if (factory!=null){
            sqlSession = factory.openSession();//非自动提交事务

        }
        return sqlSession;
    }

}
