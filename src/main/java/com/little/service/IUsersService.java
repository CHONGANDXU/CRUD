package com.little.service;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */
public interface IUsersService{
    Map<String,Object> checkPassword(String username, String password);
}
