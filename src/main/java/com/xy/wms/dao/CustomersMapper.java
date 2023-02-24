package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.Customers;

import java.util.List;
import java.util.Map;

public interface CustomersMapper extends BaseMapper<Customers,Integer> {


    List<Map<String, Object>> queryAllCustomers();

    Customers queryCustomersByName(String name);

}