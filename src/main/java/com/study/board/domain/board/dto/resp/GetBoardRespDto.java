package com.study.board.domain.board.dto.resp;

import com.study.board.domain.board.entity.Board;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
        String title,
        String content,
        String author
) {
    public static GetBoardRespDto from(Board board) {
        return GetBoardRespDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .author("test")
                .build();
    }
}
