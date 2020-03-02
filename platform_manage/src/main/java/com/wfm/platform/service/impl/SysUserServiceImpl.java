package com.wfm.platform.service.impl;

import com.wfm.platform.dao.BaseDao;
import com.wfm.platform.dao.SysUserMapper;
import com.wfm.platform.entities.SysUser;
import com.wfm.platform.service.SysUserService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
    public SysUser login(SysUser suser) {
        SysUser suser_old = userDao.findByName(suser.getLoginname());
        if (null != suser_old && pwdEncoder.matches(suser.getPassword(), suser_old.getPassword())) {
            return suser_old;
        }
        return null;
    }
}