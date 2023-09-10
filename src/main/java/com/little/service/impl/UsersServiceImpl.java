package com.little.service.impl;

import com.little.entity.Users;
import com.little.mapper.UsersMapper;
import com.little.service.IUsersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */
@Service
public class UsersServiceImpl implements IUsersService {
    @Resource
    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Map<String, Object> checkPassword(String username, String password){
        Map<String,Object> user=new HashMap<>();
        user.put("username",username);
        List<Users> users = usersMapper.selectByMap(user);
        if(users.isEmpty()){
            Map<String,Object> response=new HashMap<>();
            response.put("code",1);
            response.put("msg","账号不存在，请检查您输入的账号！！！");
            return response;
        }else{
            Users users1 = users.get(0);
            Map<String,Object> response=new HashMap<>();
            if(Objects.equals(password, users1.getPassword())){
                response.put("code",0);
                response.put("msg","登录成功！！！");
            }else{
                response.put("code",1);
                response.put("msg","密码错误，请重新输入密码！！！");
            }
            return response;
        }

    }
}
