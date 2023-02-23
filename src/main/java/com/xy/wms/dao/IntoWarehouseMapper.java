package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.IntoWarehouse;
import com.xy.wms.vo.report.DailyExpenses;

import java.util.List;

public interface IntoWarehouseMapper extends BaseMapper<IntoWarehouse,Integer> {


    List<DailyExpenses> dailyExpensesList();
}
