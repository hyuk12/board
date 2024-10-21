package com.study.board.domain.board.entity;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.user.entity.User;
import com.study.board.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boards")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Comment("게시글 제목")
    private String title;

    @Column(name = "content")
    @Comment("게시글")
    private String content;

    // 연관 관계 매핑 유저아이디 기준으로 조인을해서 가져온다 다 대 1
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public GetBoardRespDto of(String name) {
        return GetBoardRespDto.builder()
                .title(this.title)
                .content(this.content)
                .author(name)
                .build();
    }
}
