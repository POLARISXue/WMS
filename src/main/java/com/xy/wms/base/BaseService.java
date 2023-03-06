package com.xy.wms.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public abstract class BaseService<T,ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;



    /**
     * 添加记录返回行数
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertSelective(T entity) throws DataAccessException{
        return baseMapper.insertSelective(entity);
    }

    /**
     * 添加记录返回主键
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ID insertHasKey(T entity) throws DataAccessException{
        baseMapper.insertHasKey(entity);
        try {
           return (ID) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 批量添加
     * @param entities
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertBatch(List<T> entities) throws DataAccessException{
        return baseMapper.insertBatch(entities);
    }


    /**
     * 根据id 查询详情
     * @param id
     * @return
     */

    public T selectByPrimaryKey(ID id) throws DataAccessException{
        return baseMapper.selectByPrimaryKey(id);
    }


    /**
     * 多条件查询
     * @param baseQuery
     * @return
     */
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException{
        return baseMapper.selectByParams(baseQuery);
    }


    /**
     * 更新单条记录
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException{
        return baseMapper.updateByPrimaryKeySelective(entity);
    }


    /**
     * 批量更新
     * @param entities
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateBatch(List<T> entities) throws DataAccessException{
        return baseMapper.updateBatch(entities);
    }

    /**
     * 删除单条记录
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteByPrimaryKey(ID id) throws DataAccessException{
        return baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteBatch(ID[] ids,String key) throws DataAccessException{
        redisTemplate.delete(redisTemplate.keys(key+"*"));
        return baseMapper.deleteBatch(ids);
    }


    public Map<String, Object> queryByParamsForTable(BaseQuery baseQuery,String keyString) {
        //redis缓存
        StringBuffer keyBuff = new StringBuffer(keyString);
        keyBuff.append(baseQuery.getRedisKey());
        String key = keyBuff.toString();

        //依据key找值
        PageInfo<T> pageInfo = null;
        if (redisTemplate.hasKey(key)){
            pageInfo = (PageInfo<T>) valueOperations.get(key);
        }else {
            PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
            pageInfo =new PageInfo<T>(selectByParams(baseQuery));
            if (pageInfo.getTotal()>0){
                valueOperations.set(key,pageInfo);
            }
        }

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

}
