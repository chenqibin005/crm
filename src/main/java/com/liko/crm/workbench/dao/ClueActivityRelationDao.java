package com.liko.crm.workbench.dao;


import com.liko.crm.workbench.domain.ClueActivityRelation;

public interface ClueActivityRelationDao {


    int delById(String id);

    int saveActivity(ClueActivityRelation clue);
}
