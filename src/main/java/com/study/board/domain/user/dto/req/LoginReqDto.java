package com.study.board.domain.user.dto.req;

import com.study.board.domain.user.entity.User;

public record LoginReqDto(
    String username,
    String rawPassword
) {
  public User of (String password){
    return User.builder()
        .username(this.username)
        .password(password)
        .build();
  }

}
