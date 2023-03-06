package com.xy.wms.service;

import com.xy.wms.base.BaseService;
import com.xy.wms.dao.ModuleMapper;
import com.xy.wms.dao.PermissionMapper;
import com.xy.wms.model.TreeModel;
import com.xy.wms.utils.AssertUtil;
import com.xy.wms.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module, Integer> {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;


    public Map<String, Object> queryModuleList() {
        StringBuffer keyBuff = new StringBuffer("module:list");
        String key = keyBuff.toString();
        List<Module> moduleList = null;
        if (redisTemplate.hasKey(key)){
            moduleList = (List<Module>) valueOperations.get(key);
        }else {
            moduleList = moduleMapper.queryModuleList();
            if (moduleList!=null){
                valueOperations.set(key,moduleList);
            }
        }

        Map<String, Object> map = new HashMap<>();
        //查询资源数据
        //设置默认值
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", moduleList.size());
        map.put("data", moduleList);
        return map;
    }

    /**
     * 增加操作
     * @param module
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) {
        //判断module对象是否为空
        AssertUtil.isTrue(null == module,"待添加的资源不存在");
        //判断添加的数据的合理性
//        模块名称 moduleName
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"待添加姓名不能为空");
        //待添加的层级是否合法
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade||!(grade == 0 ||grade == 1 ||grade == 2),"待添加的层级不合法");
        //根据层级查询名称，名称不能重复
        AssertUtil.isTrue(null != moduleMapper.selectByGradeAndModuleNameAndParentId(grade,module.getModuleName(),module.getParentId()),"同一层级内权限名称不能重复");
        //判断层级
        if (grade == -1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"url不能为空！");
            AssertUtil.isTrue(null == moduleMapper.selectByGradeAndUrl(grade,module.getUrl()),"url不能重复");
        }
        if (grade == 0){
            module.setParentId(-1);
        }
        if (grade != 0){
            // 非空
            AssertUtil.isTrue(null == module.getParentId(),"父级菜单不能为空！");
            // 父级菜单必须存在 (将父级菜单的ID作为主键，查询资源记录)
            AssertUtil.isTrue(null == moduleMapper.selectByPrimaryKey(module.getParentId()), "请指定正确的父级菜单！");

        }
        //权限吗不能为空
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限吗不能为空！");
        //权限吗不可重复
        AssertUtil.isTrue(null != moduleMapper.selectModuleByOptValue(module.getOptValue()),"权限码已存在！！");
        //设置参数默认值
        module.setIsValid((byte) 1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        //执行添加操作
        AssertUtil.isTrue(moduleMapper.insertSelective(module) < 1,"资源添加失败");
        redisTemplate.delete(redisTemplate.keys("module:list*"));
    }

    /**
     * 修改操作
     * @param module
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {
        //参数校验
        AssertUtil.isTrue(null == module,"待修改记录传输异常");
        AssertUtil.isTrue(null == module.getId(),"待修改记录不存在");
        Module temp = moduleMapper.selectByPrimaryKey(module.getId());
        //判断待修改记录是否存在
        AssertUtil.isTrue(null == temp,"待修改记录不存在！！");
        //层级grade判断
        Integer grade =module.getGrade();
        AssertUtil.isTrue(null == grade ||!(grade == 0 || grade == 1 || grade == 2),"修改层级不满足要求");
        //模块名称判断
        temp = moduleMapper.selectByGradeAndModuleNameAndParentId(grade,module.getModuleName(),module.getParentId());
        //非空，同一层级下模块名称唯一 （不包含当前修改记录本身）
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getId()).equals(module.getId()), "该层级下菜单名已存在！");
        }
        // 地址 url   二级菜单（grade=1），非空且同一层级下不可重复（不包含当前修改记录本身）
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "菜单URL不能为空！");
            // 通过层级与菜单URl查询资源对象
            temp = moduleMapper.selectByGradeAndUrl(grade, module.getUrl());
            // 判断是否存在
            if (temp != null) {
                AssertUtil.isTrue(!(temp.getId()).equals(module.getId()), "该层级下菜单URL已存在！");
            }
        }
        // 权限码 optValue     非空，不可重复（不包含当前修改记录本身）
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"授权码不能为空");
        // 通过权限码查询资源对象
        temp = moduleMapper.selectModuleByOptValue(module.getOptValue());
        // 判断是否为空
        if (temp != null){
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"该层级下的授权码已存在");
        }

        /* 2. 设置参数的默认值  */
        // 修改时间 系统当前时间
        module.setUpdateDate(new Date());

        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1,"修改资源失败");
        redisTemplate.delete(redisTemplate.keys("module:list*"));
    }

    /**
     * 删除操作
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModule(Integer id) {
        //判断记录是否存在
        AssertUtil.isTrue(null == id,"用户记录不存在");
        Module temp = moduleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == temp,"待删除记录不存在！！");
        //如果资源存在子记录，则不可删除
        AssertUtil.isTrue(moduleMapper.selectModuleByParentId(id)>0,"资源存在子记录，不能直接删除！");
        // 通过资源id查询权限表中是否存在数据
        Integer count = permissionMapper.countPermissionByModuleId(id);
        //如果存在 删除资源时 将权限表 的记录也删除
        if (count > 0){
            // 删除指定资源ID的权限记录
            permissionMapper.deletePermissionByModuleId(id);
        }
        //执行删除 操作  将isvalid设置为0
        temp.setIsValid((byte) 0);
        temp.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(temp)<1,"用户记录删除失败！" );
        redisTemplate.delete(redisTemplate.keys("module:list*"));
    }

    public List<TreeModel> queryAllModules(Integer roleId) {
        //查询所有的资源id
        List<TreeModel> treeModelList = moduleMapper.selectAllModules();
        //查询指定用户拥有的权限
        List<Integer> permissionIds = permissionMapper.selectRoleHasModuleIdsByRoleId(roleId);
        //遍历授权
        //判断角色是否拥有资源id
        if (permissionIds != null && permissionIds.size() > 0){
            //循环所有的资源列表，判断用户拥有的资源id是否有匹配的，如果有 设置为true
            treeModelList.forEach(treeModel -> {
                        if (permissionIds.contains(treeModel.getId())){
                            //如果包括，证明被授权过 设置true
                            treeModel.setChecked(true);
                        }
                    }

            );
        }
        return treeModelList;
    }
}
