package com.xy.wms.controller;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.GoodsTypeQuery;
import com.xy.wms.service.GoodsTypeService;
import com.xy.wms.vo.GoodsType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {
    @Resource
    private GoodsTypeService goodsTypeService;
    @RequiredPermission(code = "5020")
    @RequestMapping("index")
    public String index(){
        return "goods_type/goods_type";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(GoodsTypeQuery goodsTypeQuery) {
        return goodsTypeService.queryByParamsForTable(goodsTypeQuery,"goodsType:list");
    }

    @RequestMapping("/queryAllGoodsType")
    @ResponseBody
    public List<Map<String,Object>> queryAllGoodsType(){
        return goodsTypeService.queryAllGoodsType();
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addGoodsType(GoodsType goodsType){
        goodsTypeService.addGoodsType(goodsType);
        return success("用户添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateGoodsType(GoodsType goodsType){
        goodsTypeService.updateGoodsType(goodsType);
        return success("用户修改成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteGoodsType(Integer[] ids){
        goodsTypeService.deleteBatch(ids,"goodsType:list");
        return success("用户删除成功");
    }

    @RequestMapping("openAddOrUpdateGoodsTypePage")
    public String openAddOrUpdateGoodsTypePage(Integer id, HttpServletRequest request) {

        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            GoodsType goodsType = goodsTypeService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("goodsTypeInfo", goodsType);
        }

        return "goods_type/add_update";
    }
}
