package com.little.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.entity.Permission;
import com.little.mapper.PermissionMapper;
import com.little.service.IAuthoritiesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IAuthoritiesService {
    @Resource
    private final PermissionMapper permissionMapper;

    public AuthoritiesServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

}
