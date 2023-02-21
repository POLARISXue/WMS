package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.CustomersMapper;
import com.xy.wms.vo.Customers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomersService extends BaseService<Customers,Integer> {

    @Resource
    private CustomersMapper customersMapper;

    public List<Map<String, Object>> queryAllCustomers() {
        return customersMapper.queryAllCustomers();
    }
}
