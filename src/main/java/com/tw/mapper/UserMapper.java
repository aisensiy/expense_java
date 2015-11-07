package com.tw.mapper;

import com.tw.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void createUser(@Param("user") User user);
}
