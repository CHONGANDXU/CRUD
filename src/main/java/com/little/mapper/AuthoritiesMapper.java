package com.little.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.entity.Authorities;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

}
