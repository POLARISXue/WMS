package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class GoodsTypeQuery extends BaseQuery {
    private Integer id;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (id!=null){
            key.append(":id:"+id);
        }
        if (StringUtils.isNotBlank(typeName)){
            key.append(":typeName:"+typeName);
        }
        return key;
    }
}
