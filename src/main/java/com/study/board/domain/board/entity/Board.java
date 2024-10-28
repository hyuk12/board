package com.study.board.domain.board.entity;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.user.entity.User;
import com.study.board.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity//JPA엔티티임을 나타냄
@NoArgsConstructor//둘다 꼭 써줘야 빌터랑 엔티티가 적용됨
@AllArgsConstructor//둘다
@Table(name = "boards")

public class Board extends BaseTimeEntity {
    @Id //테이블의 기본키를 지정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//db에 엔티티 추가 시 자동으로 값이 증가, 기본 키 값이 자동으로 생성
    private Long id;//인트 안댐 절대

    @Column(name="title")
    @Comment("게시글 제목")
    private String title;

    @Column(name="content")
    @Comment("게시글 내용")
    private String content;

    @Column(name = "author")
    @Comment("작성자")
    private String author;

    // update 메서드 추가
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @ManyToOne
    // 엔티티인 board가 many, 필드인 user가 one
    //연관관계 매핑 유저아이디 기준으로 조인을 해서 가져온다 다 대 일 관계
    //유저가 일이고 게시글이 다
    //@ManyToOne 어노테이션은 Board 엔티티와 User 엔티티 간의 관계를 설정
    @JoinColumn(name = "user_id") // 중요!!!!Board의 user_id는 User의 기본키(id)를 참조하는 외래키
    private User user;// user 필드는 Board 객체가 참조하는 User 객체를 의미
    // 각 게시글(Board)은 작성자(User)를 가리키며, 이로 인해 특정 User가 작성한 게시글 목록을 쉽게 조회




}
