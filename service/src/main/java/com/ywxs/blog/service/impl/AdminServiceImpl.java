package com.ywxs.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.ywxs.blog.common.entity.Admin;
import com.ywxs.blog.common.entity.vo.AdminVO;
import com.ywxs.blog.common.util.IdUtils;
import com.ywxs.blog.common.util.JwtUtil;
import com.ywxs.blog.dao.AdminMapper;
import com.ywxs.blog.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 2020-12-17
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private final AdminMapper adminMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public Map<String, Object> login(AdminVO vo) {
        String username = vo.getUsername();
        String password = vo.getPassword();
        Assert.notNull(username, "用户名为空！");
        Assert.notNull(password, "密码为空！");
        Admin login = adminMapper.login(username);
        Assert.notNull(login, "用户名或密码错误！");
        Assert.isTrue(encoder.matches(password, login.getPassword()), "用户名或密码错误！");
        String token = jwtUtil.createJWT(Convert.toStr(login.getId()), login.getUsername(), login.getRole());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("admin", login);
        return map;
    }

    @Override
    public Admin getInfo() {
        Admin admin = getById(IdUtils.getId());
        return admin;
    }

    @Override
    public void updatePwd(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        updateById(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        updateById(admin);
    }

    @Override
    public Admin code() {
        Admin code = adminMapper.getCode();
        return code;
    }

    @Override
    public Admin getInfo(Integer id) {
        Admin info = adminMapper.getInfo(id);
        return info;
    }
}
