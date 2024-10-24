package com.study.board.domain.user.entity;

import com.study.board.domain.board.entity.Board;
import com.study.board.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String name;
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards;
}
