package com.little.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.entity.Permission;
import com.little.entity.Users;
import com.little.mapper.PermissionMapper;
import com.little.mapper.UsersMapper;
import com.little.service.IUsersService;
import com.little.vo.ResultVO;
import jakarta.annotation.Resource;
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
    private final PermissionMapper permissionMapper;

    public UsersServiceImpl(UsersMapper usersMapper, PermissionMapper permissionMapper) {
        this.usersMapper = usersMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ResultVO<Object> checkPassword(String username, String password) {
        Map<String,Object> user=new HashMap<>();
        user.put("username",username);
        List<Users> users = usersMapper.selectByMap(user);
        ResultVO<Object> resultVO = new ResultVO<>();
        if(users.isEmpty()){
            resultVO.setCode(1);
            resultVO.setMessage("账号不存在，请检查您输入的账号！！！");
        }else{
            Users users1 = users.get(0);
            if(Objects.equals(password, users1.getPassword())){
                resultVO.setCode(0);
                resultVO.setMessage("登录成功！！！");
            }else{
                resultVO.setCode(1);
                resultVO.setMessage("密码错误，请重新输入密码！！！");
            }
        }
        return resultVO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", username);
        Users user = usersMapper.selectOne(usersQueryWrapper);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在，请检查您输入的账号！！！");
        }

        QueryWrapper<Permission> authoritiesQueryWrapper = new QueryWrapper<>();
        authoritiesQueryWrapper.eq("username", user.getUsername());
        List<Permission> authoritiesList = permissionMapper.selectList(authoritiesQueryWrapper);
        List<String> stringList = authoritiesList.stream().map(Permission::getPermit).toList();
        // user.setAuthorityList(AuthorityUtils.createAuthorityList(stringList));
        return user;
    }
}
