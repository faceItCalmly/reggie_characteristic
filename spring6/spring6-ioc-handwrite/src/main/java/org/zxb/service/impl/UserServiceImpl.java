package org.zxb.service.impl;

import org.zxb.anno.Bean;
import org.zxb.anno.Di;
import org.zxb.dao.UserDao;

@Bean
public class UserServiceImpl {
    @Di
    UserDao userDao;
}
