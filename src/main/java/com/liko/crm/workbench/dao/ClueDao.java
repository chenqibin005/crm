package com.liko.crm.workbench.dao;


import com.liko.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueDao {


    int saveClue(Clue clue);

    int getTotalByCondition(Map<String, Object> map);

    List<Clue> getClueListByCoindition(Map<String, Object> map);

    Clue detail(String id);
}
