package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsTypeMapper;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.GoodsType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GoodsTypeService extends BaseService<GoodsType,Integer> {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    public List<Map<String,Object>> queryAllGoodsType(){
        return goodsTypeMapper.queryAllGoodsType();
    }

    public void addGoodsType(GoodsType goodsType) {
        //参数校验
        checkGoodsType(goodsType.getId(),goodsType.getTypeName());
        //初始化赋值
        goodsType.setIsValid(1);
        //根据返回行数判断是否添加成功
        AssertUtil.isTrue(goodsTypeMapper.insertSelective(goodsType)<1,"商品类型添加失败");

    }



    public void updateGoodsType(GoodsType goodsType) {
        AssertUtil.isTrue(null == goodsType.getId(),"待修改记录不存在");
        AssertUtil.isTrue(null == goodsTypeMapper.selectByPrimaryKey(goodsType.getId()),"待修改记录不存在！！");
        //参数校验
        checkGoodsType(goodsType.getId(),goodsType.getTypeName());
        //执行修改操作并赋值
        AssertUtil.isTrue(goodsTypeMapper.updateByPrimaryKeySelective(goodsType)<1,"用户修改失败");

    }
    private void checkGoodsType(Integer id, String typeName) {
        AssertUtil.isTrue(null == id,"商品类型id不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(typeName),"商品类型名称不能为空！");
        GoodsType temp = goodsTypeMapper.selectByTypeName(typeName);
        AssertUtil.isTrue(null != temp &&!(temp.getId().equals(id)),"商品类型不能重复");

    }
}
