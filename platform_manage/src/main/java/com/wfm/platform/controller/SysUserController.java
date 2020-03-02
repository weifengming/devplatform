package com.wfm.platform.controller;

import com.wfm.platform.entities.Result;
import com.wfm.platform.entities.SysUser;
import com.wfm.platform.exception.StatusCode;
import com.wfm.platform.query.PageList;
import com.wfm.platform.query.QueryFilter;
import com.wfm.platform.service.SysUserService;
import com.wfm.platform.util.JWTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分页获取用户信息", httpMethod = "POST", notes = "分页获取用户信息")
    @ApiParam(name = "queryFilter", value = "通用查询对象")
    @RequestMapping(value = "/user/getUserPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public PageList<SysUser> getUserPage(@RequestBody QueryFilter queryFilter) {
        return sysUserService.query(queryFilter);
    }

    @ApiOperation(value = "添加用户信息", httpMethod = "POST", notes = "添加用户信息")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result save(@RequestBody SysUser suser) {
        sysUserService.create(suser);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录")
    @ApiParam(name = "suser", value = "用户对象")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result login(@RequestBody SysUser suser) {
        SysUser suserLogin = sysUserService.login(suser);
        if (null == suserLogin) {
            return new Result(true, StatusCode.LOGINERROR, "登录失败");
        }
        return new Result(true, StatusCode.OK, "登录成功", "");
    }

    /*@ApiOperation(value = "获取用户信息", notes = "分页获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/findPage/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUsers = sysUserService.selectPage();
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<SysUser>(pageInfo.getTotal(), pageInfo.getList()));
    }*/

    /*@PostMapping(value = "/login")
    public Result login(@RequestBody SysUser suser) {
        int i = 1 / 0;
        SysUser suserLogin = sysUserService.login(suser);
        if (null == suserLogin) {
            return new Result(true, StatusCode.LOGINERROR, "登录失败");
        }

        String token = jwToken.generateToken(suserLogin.getLoginname());
        Map<String, Object> map = new HashMap<>();
        map.put("loginname", suserLogin.getLoginname());
        map.put("token", token);
        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    @GetMapping(value = "/findById/{userId}")
    public Result findById(@PathVariable("userId") String userId) {
        SysUser suser = sysUserService.findById(userId);
        return new Result(true, StatusCode.OK, "查询成功", suser);
    }

    @PostMapping(value = "/save")
    public Result save(@RequestBody SysUser suer) {
        sysUserService.save(suer);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PostMapping(value = "/update/{userId}")
    public Result update(@PathVariable(value = "userId") String userId, @RequestBody SysUser suer) {
        suer.setId(userId);
        sysUserService.update(suer);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @GetMapping(value = "/deleteById/{userId}")
    public Result deleteById(@PathVariable("userId") String userId) {
        sysUserService.deleteById(userId);
        return new Result(true, StatusCode.OK, "删除成功");
    }*/

}
