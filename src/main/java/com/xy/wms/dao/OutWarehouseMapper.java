package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.vo.OutWarehouse;
import com.xy.wms.vo.report.RadarChart;
import com.xy.wms.vo.report.SaleTotalPriceByDay;

import java.util.List;

public interface OutWarehouseMapper extends BaseMapper<OutWarehouse,Integer> {

    List<SaleTotalPriceByDay> saleTotalPriceByDay();

    List<Integer> loadRadarChart();
}