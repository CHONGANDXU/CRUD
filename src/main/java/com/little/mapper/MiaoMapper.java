package com.little.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.entity.Miao;
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
public interface MiaoMapper extends BaseMapper<Miao>{

}
