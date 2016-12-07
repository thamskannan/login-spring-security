package com.login.auth.service;

import com.login.auth.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
