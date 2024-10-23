package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import java.time.LocalDateTime;

// java 15 이후 나온 클래스
public record CreateBoardReqDto(
        String title,
        String content
) {
    public Board of(Long userId) {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .userId(userId)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
