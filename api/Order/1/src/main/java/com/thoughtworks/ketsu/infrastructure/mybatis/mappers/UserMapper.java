package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User findById(long id);

    int save(@Param("info") Map<String, Object> info);

    User findByEmail(String email);
}
