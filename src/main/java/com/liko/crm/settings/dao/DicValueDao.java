package com.liko.crm.settings.dao;

import com.liko.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:46
 */
public interface DicValueDao {
    List<DicValue> getDicValue(String code);
}
