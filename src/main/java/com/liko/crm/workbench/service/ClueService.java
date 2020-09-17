package com.liko.crm.workbench.service;

import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.domain.Clue;

import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:39
 */
public interface ClueService {

    boolean saveClue(Clue clue);

    Pagination<Clue> pageList(Map<String, Object> map);

    Clue detail(String id);

    boolean delById(String id);


    boolean saveActivity(String[] aids, String clueId);
}
