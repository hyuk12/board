package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import java.time.LocalDateTime;

public record UpdateBoardReqDto(
        String title,
        String content
) {
    public Board of() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
