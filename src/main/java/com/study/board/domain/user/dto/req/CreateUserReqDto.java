package com.study.board.domain.user.dto.req;

import com.study.board.domain.user.entity.User;

import java.time.LocalDateTime;

public record CreateUserReqDto(
        String username,
        String rawPassword,
        String email,
        String name
) {
    public User of(String password) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
