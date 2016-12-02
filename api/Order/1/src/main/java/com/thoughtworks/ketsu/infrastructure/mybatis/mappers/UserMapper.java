package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.User;

public interface UserMapper {

    User findById(long id);

}
