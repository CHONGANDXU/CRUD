package com.little.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.entity.Users;
import com.little.vo.ResultVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

public interface IUsersService extends IService<Users>, UserDetailsService {
    ResultVO<Object> checkPassword(String username, String password);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
