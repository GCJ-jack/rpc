package com.chaojun.server;

import cn.hutool.core.util.IdUtil;
import com.chaojun.api.User;
import com.chaojun.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Long id) {
        return User.builder().
                id(id).
                name(IdUtil.fastSimpleUUID()).
                build();
    }
}
