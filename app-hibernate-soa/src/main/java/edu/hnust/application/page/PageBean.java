package edu.hnust.application.page;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageBean<T> implements Serializable {
    
    private int pageIndex; // 当前第几页    
    private int pageSize; // page 每页记录数    
    private int total; // page 总记录数    
    private int pageTotal = 0; // page 总页数    
    private List<T> datas; // page 封装的数据列表
    
    public PageBean() {
        super();
    }
    
    public PageBean(int currentNo, int pageSize) {
        super();
        this.pageIndex = currentNo;
        this.pageSize = pageSize;
    }
    
    public PageBean(List<T> datas, int total, int pageSize) {
        super();
        this.total = total;
        this.datas = datas;
        this.pageSize = pageSize;
    }
    
    public PageBean(List<T> datas, int total, int pageSize, int pageNumber) {
        super();
        this.total = total;
        this.datas = datas;
        this.pageSize = pageSize;
        this.pageIndex = pageNumber;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public List<T> getDatas() {
        return datas;
    }
    
    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
    
    public int getPageTotal() {
        try {
            int page_total = total % pageSize == 0 ? total / pageSize : (total - total % pageSize) / pageSize + 1;
            return this.pageTotal = page_total;
        } catch (Exception e) {
            e.printStackTrace();
            return pageTotal;
        }
    }
    
    public void setPageTotal(int pageCount) {
        this.pageTotal = pageCount;
    }
    
    public int getPageIndex() {
        return pageIndex;
    }
    
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
