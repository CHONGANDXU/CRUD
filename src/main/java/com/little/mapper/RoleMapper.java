package com.little.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT r.id, r.name, r.remark " +
            " FROM tb_role r " +
            "   LEFT JOIN tb_user_role ur " +
            "     ON r.id = ur.role_id " +
            " WHERE ur.user_id=#{userId}")
    List<Role> selectRolesByUserId(Integer userId);
}
