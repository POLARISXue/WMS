package com.xy.wms.base;



public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public StringBuffer getRedisKey(){
        return new StringBuffer(":page:"+getPage()+":limit:"+getLimit());
    }
}
