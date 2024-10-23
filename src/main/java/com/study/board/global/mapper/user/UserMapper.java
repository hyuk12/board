package com.study.board.global.mapper.user;

import com.study.board.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void createUser(User user);

    User findByUsername(@Param("username") String username);

    User findByUserId(@Param("id") Long userId);
}
