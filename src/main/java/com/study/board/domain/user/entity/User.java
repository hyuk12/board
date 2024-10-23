package com.study.board.domain.user.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
