package com.liko.crm.settings.service.impl;

import com.liko.crm.settings.dao.DicTypeDao;
import com.liko.crm.settings.dao.DicValueDao;
import com.liko.crm.settings.domain.DicType;
import com.liko.crm.settings.domain.DicValue;
import com.liko.crm.settings.service.DicValueService;
import com.liko.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:50
 */
public class DicValueServiceImpl  implements DicValueService {
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);
private DicTypeDao dicTypeDao =SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    @Override
    public Map<String, List<DicValue>> getAll() {
        List<DicType> type =dicTypeDao.getType();
        System.out.println(type);
        Map<String,List<DicValue>> map =new HashMap<>();
        for (DicType code :type){
            List<DicValue> dicValuelist=dicValueDao.getDicValue(code.getCode());
            map.put(code.getCode(),dicValuelist);
        }
        return map;
    }
}
