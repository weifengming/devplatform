package com.wfm.platform.controller;

import com.wfm.platform.entities.Result;
import com.wfm.platform.entities.SysUser;
import com.wfm.platform.exception.StatusCode;
import com.wfm.platform.query.PageList;
import com.wfm.platform.query.QueryFilter;
import com.wfm.platform.service.SysUserService;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.JWTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weifengming
 * @description 用户控制器
 * @date 2020/2/3
 */
@Api(value = "API - SysUserController", description = "用户管理")
@CrossOrigin
@RestController
@RequestMapping("/api/manage/suser/v1")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JWTokenUtil jwToken;

    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result login(@RequestBody SysUser suser) {
        SysUser suserLogin = sysUserService.login(suser);
        if (null == suserLogin) {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        String token = jwToken.generateToken(suserLogin.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("loginname", suserLogin.getUsername());
        map.put("token", token);
        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    @ApiOperation(value = "用户退出", httpMethod = "POST", notes = "用户退出")
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result login() {
        return new Result(true, StatusCode.OK, "操作成功");
    }


    @ApiOperation(value = "分页获取用户信息", httpMethod = "POST", notes = "分页获取用户信息")
    @ApiParam(name = "queryFilter", value = "通用查询对象")
    @RequestMapping(value = "/user/getUserPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result getUserPage(@RequestBody QueryFilter queryFilter) {
        try {
            PageList<SysUser> query = sysUserService.query(queryFilter);
            return new Result(true, StatusCode.OK, "分页获取用户信息成功", query);
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "添加用户信息", httpMethod = "POST", notes = "添加用户信息")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result save(@RequestBody SysUser suser) {
        try {
            sysUserService.addUser(suser);
            return new Result(true, StatusCode.OK, "添加成功");
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "更新用户信息", httpMethod = "POST", notes = "更新用户信息")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/updateById", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result updateById(@RequestBody SysUser suser) {
        try {
            sysUserService.updateById(suser);
            return new Result(true, StatusCode.OK, "更新成功");
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "删除用户信息", httpMethod = "DELETE", notes = "更新用户信息")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/deleteById", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public Result deleteById(String id) {
        try {
            sysUserService.deleteById(id);
            return new Result(true, StatusCode.OK, "删除成功");
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "获取用户信息", httpMethod = "GET", notes = "获取用户信息")
    @RequestMapping(value = "/user/getInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Result getInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        SysUser suser = sysUserService.getUserByLoginName(username);
        if (BeanUtils.isNotEmpty(suser)) {
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            //用户头像
            map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            return new Result(true, StatusCode.OK, "操作成功", map);
        }
        return new Result(false, StatusCode.ERROR, "操作失败");
    }

}
