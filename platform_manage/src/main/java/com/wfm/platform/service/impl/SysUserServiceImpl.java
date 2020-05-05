package com.wfm.platform.service.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.wfm.platform.dao.BaseDao;
import com.wfm.platform.dao.SysUserMapper;
import com.wfm.platform.entities.SysUser;
import com.wfm.platform.exception.NotFoundException;
import com.wfm.platform.exception.RequiredException;
import com.wfm.platform.service.SysUserService;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.IdWorker;
import com.wfm.platform.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SysUserServiceImpl extends ServiceImpl<String, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;


    @Override
    protected BaseDao<String, SysUser> getDao() {
        return this.userDao;
    }

    /**
     * 用户登录
     *
     * @param suser
     * @return
     */
    @Override
    public SysUser login(SysUser suser) {
        SysUser suser_old = userDao.findByName(suser.getUsername());
        // 演示环境下密码暂时不做验证 /* && pwdEncoder.matches(suser.getPassword(), suser_old.getPassword())*/
        if (null != suser_old) {
            return suser_old;
        }
        return null;
    }

    /**
     * 添加用户
     *
     * @param suser
     */
    @Override
    public void addUser(SysUser suser) {
        suser.setPassword("admin111111");
        if (StringHelper.isEmpty(suser.getUsername())) {
            throw new RequiredException("添加用户失败，用户【loginname】必填！");
        }
        //判断登录名是否存在
        SysUser suserByLoginname = getUserByLoginName(suser.getUsername());
        if (BeanUtils.isNotEmpty(suserByLoginname)) {
            throw new RequiredException(new StringBuilder().append("添加用户失败，账号【").append(suser.getUsername()).append("】已经存在").toString());
        }
        if (StringHelper.isEmpty(suser.getTelphone())) {
            throw new RequiredException("添加用户失败，用户【mobile】必填！");
        }
        //判断手机号是否存在
        SysUser suserByMobile = getByMobile(suser.getTelphone());
        if (BeanUtils.isNotEmpty(suserByMobile)) {
            throw new RequiredException(new StringBuilder().append("添加用户失败，手机号【").append(suser.getTelphone()).append("】已经存在").toString());
        }
        if (StringHelper.isEmpty(suser.getUsername())) {
            throw new RequiredException("添加用户失败，用户【username】必填！");
        }
        if (StringHelper.isEmpty(suser.getPassword())) {
            throw new RequiredException("添加用户失败，用户【password】必填！");
        }
        suser.setDelFlag(Integer.valueOf(2));
        suser.setStatus(Integer.valueOf(1));
        suser.setId(idWorker.nextId() + "");
        suser.setCreateTime(LocalDateTime.now());
        suser.setPassword(pwdEncoder.encode(suser.getPassword()));

        try {
            getDao().create(suser);
        } catch (Exception e) {
            if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
                throw new RuntimeException(new StringBuilder().append("添加用户失败，帐号【").append(suser.getUsername()).append("】已存在，请重先通过“deletePhysical”接口物理删除！").toString());
            }
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void updateById(SysUser suser) {
        if (StringHelper.isEmpty(suser.getUsername())) {
            throw new RequiredException("更新用户失败，用户帐号【account】必填！");
        }
        //判断登录名是否存在
        SysUser suserByLoginname = getUserByLoginName(suser.getUsername());
        if (BeanUtils.isEmpty(suserByLoginname)) {
            throw new RequiredException(new StringBuilder().append("更新用户失败，根据账号【").append(suser.getUsername()).append("】没有找到对应的用户信息！").toString());
        }
        suserByLoginname.setRealname(suser.getRealname());
        suserByLoginname.setSex(suser.getSex());
        suserByLoginname.setTelphone(suser.getTelphone());
        suserByLoginname.setEmail(suser.getEmail());
        suserByLoginname.setOrgId(suser.getOrgId());
        suserByLoginname.setStatus(suser.getStatus());
        suserByLoginname.setComment(suser.getComment());

        update(suserByLoginname);
    }

    public void deleteById(String id) {
        SysUser sysUser = get(id);
        if (BeanUtils.isEmpty(sysUser)) {
            throw new NotFoundException("删除用户失败，用户帐号【account】不存在！");
        }
        sysUser.setDelFlag(1);

        update(sysUser);
    }

    @Override
    public SysUser getUserByLoginName(String loginname) {
        return userDao.findByName(loginname);
    }

    public SysUser getByMobile(String telphone) {
        return userDao.findByMobile(telphone);
    }
}