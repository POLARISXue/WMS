package com.xy.wms.service;

import com.xy.wms.base.ResultInfo;
import com.xy.wms.dao.OutWarehouseMapper;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.vo.report.InventoryOverviewQuery;
import com.xy.wms.vo.report.SaleTotalPriceByDay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataReportService {

    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private OutWarehouseMapper outWarehouseMapper;

    public ResultInfo inventoryOverview(){
        ResultInfo resultInfo = new ResultInfo();
        List<InventoryOverviewQuery> InventoryOverviewQueryList = warehouseMapper.inventoryOverview();


        if (InventoryOverviewQueryList!=null && InventoryOverviewQueryList.size()>0){
            List<String> goodsNameList = new ArrayList<>();
            List<Integer> goodsNumberList = new ArrayList<>();
            for (InventoryOverviewQuery inventoryOverviewQuery : InventoryOverviewQueryList){
                goodsNameList.add(inventoryOverviewQuery.getGoodsName());
                goodsNumberList.add(inventoryOverviewQuery.getGoodsNumber());
            }

            Map<String,List> inventoryOverviewMap = new HashMap<>();
            inventoryOverviewMap.put("goodsNameList",goodsNameList);
            inventoryOverviewMap.put("goodsNumberList",goodsNumberList);
            resultInfo.setResult(inventoryOverviewMap);
            return resultInfo;
        }
        resultInfo.setCode(405);
        resultInfo.setMsg("查询数据失败或无数据");
        return resultInfo;

    }

    public ResultInfo saleTotalPriceByDay() {
        ResultInfo resultInfo = new ResultInfo();
        List<SaleTotalPriceByDay> saleTotalPriceByDayList = outWarehouseMapper.saleTotalPriceByDay();
        if (saleTotalPriceByDayList != null && saleTotalPriceByDayList.size()>0){
            List<String> groupNameList = new ArrayList<>();
            List<Integer> salePriceList = new ArrayList<>();
            for (SaleTotalPriceByDay saleTotalPriceByDay : saleTotalPriceByDayList){
                groupNameList.add(saleTotalPriceByDay.getGroupName());
                salePriceList.add(saleTotalPriceByDay.getSalePrice());
            }
            Map<String,List> saleTotalPriceByDayMap = new HashMap<>();
            saleTotalPriceByDayMap.put("groupNameList",groupNameList);
            saleTotalPriceByDayMap.put("salePriceList",salePriceList);
            resultInfo.setResult(saleTotalPriceByDayMap);
            return resultInfo;

        }
        resultInfo.setCode(405);
        resultInfo.setMsg("查询数据失败或无数据");
        return resultInfo;
    }
}
