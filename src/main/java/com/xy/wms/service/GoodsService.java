package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.GoodsMapper;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.Goods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService extends BaseService<Goods,Integer> {

    @Resource
    private GoodsMapper goodsMapper;

    public List<Map<String,Object>> queryAllGoods(){
        return goodsMapper.queryAllGoods();
    }

    /**
     * 添加操作
     * @param goods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGoods(Goods goods) {
        //判断数据
        checkGoodsParams(goods.getGoodsName(),goods.getGoodsTypeId(),goods.getGoodsCostPrice(),goods.getGoodsSalePrice(),goods.getId());
        //初始化值
        goods.setIsValid(1);
        goods.setCreateDate(new Date());
        goods.setUpdateDate(new Date());
        //执行添加操作返回作用行数进行判断
        AssertUtil.isTrue(goodsMapper.insertSelective(goods) < 1,"商品添加失败");

    }

    /**
     * 修改操作
     * @param goods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGoods(Goods goods) {
        //参数判断
        AssertUtil.isTrue(null == goods.getId(),"待修改记录不存在");
        AssertUtil.isTrue(null == goodsMapper.selectByPrimaryKey(goods.getId()),"待修改记录不存在");
        checkGoodsParams(goods.getGoodsName(),goods.getGoodsTypeId(),goods.getGoodsCostPrice(),goods.getGoodsSalePrice(),goods.getId());
        //更改修改时间
        goods.setUpdateDate(new Date());
        //执行修改操作，进行判断
        AssertUtil.isTrue(goodsMapper.updateByPrimaryKeySelective(goods) < 1,"用户修改失败");
    }

    /**
     * 删除操作
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length < 0,"待删除记录不存在");
        AssertUtil.isTrue(goodsMapper.deleteBatch(ids)<ids.length,"用户记录删除失败");

    }

    /**
     * 参数判断
     * @param goodsName
     * @param goodsTypeId
     * @param goodsCostPrice
     * @param goodsSalePrice
     */
    private void checkGoodsParams(String goodsName, Integer goodsTypeId, Double goodsCostPrice, Double goodsSalePrice,Integer goodsId) {
        AssertUtil.isTrue(StringUtils.isBlank(goodsName),"商品名不能为空");
        //商品名唯一
        Goods temp = goodsMapper.selectByGoodsName(goodsName);
        AssertUtil.isTrue(null != temp &&!(temp.getId().equals(goodsId)),"商品名不能重复");
        AssertUtil.isTrue(null == goodsTypeId,"商品类型不能为空");
        AssertUtil.isTrue(null == goodsCostPrice,"商品进价不能为空");
        AssertUtil.isTrue(null == goodsSalePrice,"商品售价不能为空");

    }

}
