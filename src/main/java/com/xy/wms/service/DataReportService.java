package com.xy.wms.service;

import com.xy.wms.base.ResultInfo;
import com.xy.wms.dao.IntoWarehouseMapper;
import com.xy.wms.dao.OutWarehouseMapper;
import com.xy.wms.dao.WarehouseMapper;
import com.xy.wms.vo.report.DailyExpenses;
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
    @Resource
    private IntoWarehouseMapper intoWarehouseMapper;

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

    public ResultInfo dailySalesExpenses() {
        ResultInfo resultInfo = new ResultInfo();
        Map<String,List> saleTotalPriceByDayMap = new HashMap<>();
        List<SaleTotalPriceByDay> saleTotalPriceByDayList = outWarehouseMapper.saleTotalPriceByDay();
        if (saleTotalPriceByDayList != null && saleTotalPriceByDayList.size()>0){
            List<String> saleGroupNameList = new ArrayList<>();
            List<Integer> salePriceList = new ArrayList<>();
            for (SaleTotalPriceByDay saleTotalPriceByDay : saleTotalPriceByDayList){
                saleGroupNameList.add(saleTotalPriceByDay.getGroupName());
                salePriceList.add(saleTotalPriceByDay.getSalePrice());
            }
            saleTotalPriceByDayMap.put("saleGroupNameList",saleGroupNameList);
            saleTotalPriceByDayMap.put("salePriceList",salePriceList);
        }

        List<DailyExpenses> dailyExpensesList = intoWarehouseMapper.dailyExpensesList();
        if (dailyExpensesList!=null&& dailyExpensesList.size()>0){
            List<String> expensesGroupNameList = new ArrayList<>();
            List<Integer> expensesPriceList = new ArrayList<>();
            for (DailyExpenses dailyExpenses : dailyExpensesList){
                expensesGroupNameList.add(dailyExpenses.getGroupName());
                expensesPriceList.add(dailyExpenses.getDailyExpenses());
            }
            saleTotalPriceByDayMap.put("expensesGroupNameList",expensesGroupNameList);
            saleTotalPriceByDayMap.put("expensesPriceList",expensesPriceList);
            resultInfo.setResult(saleTotalPriceByDayMap);
            return resultInfo;
        }
        resultInfo.setCode(405);
        resultInfo.setMsg("查询数据失败或无数据");
        return resultInfo;
    }
}
