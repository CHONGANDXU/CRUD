package com.little.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.entity.Who;
import com.little.mapper.WhoMapper;
import com.little.service.IWhoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */
@Service
public class WhoServiceImpl extends ServiceImpl<WhoMapper, Who> implements IWhoService {

    @Resource
    private final WhoMapper whoMapper;

    public WhoServiceImpl(WhoMapper whoMapper) {
        this.whoMapper = whoMapper;
    }

}
