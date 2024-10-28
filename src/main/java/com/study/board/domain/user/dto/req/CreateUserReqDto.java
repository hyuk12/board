package com.study.board.domain.user.dto.req;

import com.study.board.domain.user.entity.User;

public record CreateUserReqDto(
    String username,
    String password,
    String email,
    String name

) {
  public User of(String password){//of는  CreateUserReqDto 객체의 필드 값을 사용하여 User 엔티티 객체를 생성

    return User.builder()
        .username(this.username)
        .password(password)
        .email(this.email)
        .name(this.name)
        .build();

  }
}
