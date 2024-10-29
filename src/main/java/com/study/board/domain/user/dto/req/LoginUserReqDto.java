package com.study.board.domain.user.dto.req;

import com.study.board.domain.user.entity.User;

public record LoginUserReqDto(
        String username,
        String rawPassword
) {
    public User of (String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
