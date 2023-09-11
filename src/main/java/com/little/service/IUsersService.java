package com.little.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */
public interface IUsersService extends IService<Users>, UserDetailsService {
    Map<String,Object> checkPassword(String username, String password);
}
