package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.interfaces.RoleDao;
import com.epam.petersburg.ncr41.dao.impl.RoleDaoImpl;
import com.epam.petersburg.ncr41.dao.interfaces.UserDao;
import com.epam.petersburg.ncr41.dao.impl.UserDaoImpl;
import com.epam.petersburg.ncr41.executors.PasswordHashing;
import com.epam.petersburg.ncr41.model.Role;
import com.epam.petersburg.ncr41.model.User;


public class LoginService {

    private UserDao userDao = new UserDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    public User verification(String email, String password) {

        User user = userDao.findByEmail(email);
        if (user != null) {
            if (PasswordHashing.validatePass(password.toCharArray(), user.getPassword())) {
                return user;
            } else {
                user = null;
            }
        }
        return user;
    }

    public Role checkRole(int role_id) {
        Role role;
        role = roleDao.findOne(role_id);
        return role;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}

