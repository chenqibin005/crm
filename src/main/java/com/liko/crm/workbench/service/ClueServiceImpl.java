package com.liko.crm.workbench.service;

import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.utils.UUIDUtil;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.dao.ClueActivityRelationDao;
import com.liko.crm.workbench.dao.ClueDao;
import com.liko.crm.workbench.domain.Clue;
import com.liko.crm.workbench.domain.ClueActivityRelation;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:39
 */
public  class ClueServiceImpl implements ClueService{
   private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
   private ClueActivityRelationDao clueActivityRelationDao= SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

   @Override
   public boolean saveActivity(String[] aids, String clueId) {
      boolean flag =true;

      for(String aid :aids){
         ClueActivityRelation clue =new ClueActivityRelation();
         clue.setId(UUIDUtil.getUUID());
         clue.setActivityId(aid);
         clue.setClueId(clueId);
         int count =clueActivityRelationDao.saveActivity(clue);
         if (count!=1){
            flag =false;
         }
      }


      return flag;
   }

   @Override
   public boolean delById(String id) {
      boolean flag =true;
      int count =clueActivityRelationDao.delById(id);
      if (count!=1){
         flag =false;
      }
      return flag;
   }



   @Override
   public Clue detail(String id) {
      Clue clue =clueDao.detail(id);
      return clue;
   }

   @Override
   public boolean saveClue(Clue clue) {
      int count =clueDao.saveClue(clue);
      boolean flag =true;
      if (count!=1){
         flag=false;
      }
      return flag;
   }

   @Override
   public Pagination<Clue> pageList(Map<String, Object> map) {
      Pagination<Clue> pagination =new Pagination<>();
      int total = clueDao.getTotalByCondition(map);
      List<Clue> clueList =clueDao.getClueListByCoindition(map);
      pagination.setTotal(total);
      pagination.setList(clueList);
      return pagination;
   }
}
