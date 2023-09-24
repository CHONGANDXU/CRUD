package com.little.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select distinct p.id, p.name,P.url,P.parent_id as parentId, P.type,p.permit,P.remark from tb_permission p" +
            "left join tb_role_permission rp" +
            "on p.id = rp.permission_id" +
            "left join tb_user_role ur" +
            "on ur.role_id = rp.role_id" +
            "where ur.user_id = #｛userId｝")
    List<Permission> selectPermissionByUserId(Integer userId);
}
