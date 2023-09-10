package com.little.controller;

import com.little.service.impl.UsersServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
class UsersController{
    private final UsersServiceImpl usersService;

    UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "UsersController类的check方法，对应/login接口")
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> check(@RequestBody Map<String,String> map){
        String userName = map.get("userName");
        String passWord = map.get("passWord");
        return usersService.checkPassword(userName,passWord);
    }
}
