package com.liko.crm.settings.service.impl;

import com.liko.crm.settings.dao.DicValueDao;
import com.liko.crm.settings.service.DicValueService;
import com.liko.crm.utils.SqlSessionUtil;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:50
 */
public class DicValueServiceImpl  implements DicValueService {
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

}
