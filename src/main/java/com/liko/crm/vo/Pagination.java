package com.liko.crm.vo;

import java.util.List;

/**
 * @author hangzhi1063
 * @date 2020/9/2 13:46
 */
public class Pagination<T> {
   private int total;
   private List<T> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
