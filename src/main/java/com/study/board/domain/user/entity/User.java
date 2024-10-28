package com.study.board.domain.user.entity;

import com.study.board.domain.board.entity.Board;
import com.study.board.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

  @Id //테이블의 기본키 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY)//db에 엔티티추가 시 자동증가
  private Long id;

  @Column(name = "username", unique = true)
  @Comment("아이디")
  private String username;

  @Column(name = "password")
  @Comment("패스워드")
  private String password;

  @Column(name = "email", unique = true)
  @Comment("이메일")
  private String email;

  @Column(name = "name")
  @Comment("이름")
  private String name;

  @Column(name = "age")
  @Comment("나이")
  private int age;

 // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 // private List<Board> boards;


}
