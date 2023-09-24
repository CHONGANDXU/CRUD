package com.little.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.entity.Authorities;
import com.little.mapper.AuthoritiesMapper;
import com.little.service.IAuthoritiesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {
    @Resource
    private final AuthoritiesMapper authoritiesMapper;

    public AuthoritiesServiceImpl(AuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

}
