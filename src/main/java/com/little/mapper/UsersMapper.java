package com.little.mapper;

import com.little.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

@Repository
@Mapper
public interface UsersMapper extends BaseMapper<Users>{

}
