package com.little.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.entity.Authorities;
import com.little.entity.Users;
import com.little.mapper.AuthoritiesMapper;
import com.little.mapper.UsersMapper;
import com.little.service.IUsersService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Resource
    private final UsersMapper usersMapper;

    @Resource
    private final AuthoritiesMapper authoritiesMapper;

    public UsersServiceImpl(UsersMapper usersMapper, AuthoritiesMapper authoritiesMapper) {
        this.usersMapper = usersMapper;
        this.authoritiesMapper = authoritiesMapper;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> usersQueryWrapperWrapper = new QueryWrapper<>();
        usersQueryWrapperWrapper.eq("username", username);
        Users user = usersMapper.selectOne(usersQueryWrapperWrapper);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在，请检查您输入的账号！！！");
        }

        QueryWrapper<Authorities> authoritiesQueryWrapper = new QueryWrapper<>();
        authoritiesQueryWrapper.eq("username", user.getUsername());
        List<Authorities> authoritiesList = authoritiesMapper.selectList(authoritiesQueryWrapper);
        List<String> stringList = authoritiesList.stream().map(Authorities::getAuthority).toList();
        user.setAuthorityList(AuthorityUtils.createAuthorityList(stringList));
        return null;
    }
}
