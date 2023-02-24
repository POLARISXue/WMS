package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.Supplier;


public interface SupplierMapper extends BaseMapper<Supplier,Integer> {

    Supplier querySupplierByName(String name);

}
