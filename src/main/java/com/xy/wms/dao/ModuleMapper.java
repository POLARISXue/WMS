package com.xy.wms.dao;

import com.xy.wms.base.BaseMapper;
import com.xy.wms.model.TreeModel;
import com.xy.wms.vo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    List<Module> queryModuleList();


    Module selectModuleByOptValue(String optValue);

    Module selectByGradeAndUrl(@Param("grade")Integer grade,@Param("url") String url);

    Integer selectModuleByParentId(Integer id);

    List<TreeModel> selectAllModules();

    Module selectByGradeAndModuleNameAndParentId(@Param("grade") Integer grade, @Param("moduleName")String moduleName,@Param("parentId") Integer parentId);
}