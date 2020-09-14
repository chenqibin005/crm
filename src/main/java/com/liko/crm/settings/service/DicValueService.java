package com.liko.crm.settings.service;

import com.liko.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:50
 */
public interface DicValueService {
    Map<String, List<DicValue>> getAll();

}
