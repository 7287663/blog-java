package com.ywxs.blog.common.util;


import java.util.List;

public class PageResult<T> {
    private Long total;
    private Long pages;
    private List<T> list;
    public PageResult(Long total, Long pages, List<T> list) {
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
