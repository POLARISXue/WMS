package com.xy.wms.controller;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.base.BaseController;
import com.xy.wms.base.ResultInfo;
import com.xy.wms.query.GoodsQuery;
import com.xy.wms.service.GoodsService;
import com.xy.wms.vo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/queryAllGoods")
    @ResponseBody
    public List<Map<String,Object>> queryAllGoods(){
        return goodsService.queryAllGoods();
    }
    @RequiredPermission(code = "5010")
    @RequestMapping("index")
    public String index() {
        return "goods/goods";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(GoodsQuery goodsQuery) {
        return goodsService.queryByParamsForTable(goodsQuery,"goods:list");
    }
    @RequiredPermission(code = "501002")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addGoods(Goods goods) {
        goodsService.addGoods(goods);
        return success("物品添加成功！！");
    }
    @RequiredPermission(code = "501004")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateGoods(Goods goods) {
        goodsService.updateGoods(goods);
        return success("物品更新成功！");
    }

    @RequestMapping("openAddOrUpdateGoodsPage")
    public String toAddOrUpdateGoodsPage(Integer id, HttpServletRequest request) {

        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            Goods goods = goodsService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("goodsInfo", goods);
        }

        return "goods/add_update";
    }

    /**
     * 用户删除
     * <p>
     * <p>
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @RequiredPermission(code = "501003")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteGoods(Integer[] ids) {

        goodsService.deleteByIds(ids);

        return success("用户删除成功！");
    }

    @RequestMapping("queryAllGoodsType")
    @ResponseBody
    public List<Map<String,Object>> queryAllGoodsType(){
        return goodsService.queryAllGoodsType();
    }

}
