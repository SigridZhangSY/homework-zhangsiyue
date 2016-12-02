package com.thoughtworks.ketsu.infrastructure.repositories;

import com.google.inject.Inject;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import java.util.Map;
import java.util.Optional;

public class UserRepository implements Users {
    @Inject
    UserMapper userMapper;

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Override
    public User createUser(Map<String, Object> info) {
        userMapper.save(info);
        return userMapper.findById(Integer.valueOf(String.valueOf(info.get("id"))));
    }

    @Override
    public Optional<User> findBy(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }
}
