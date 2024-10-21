package com.study.board.domain.board.dto.resp;

import lombok.Builder;

import java.util.List;

@Builder
public record GetBoardPageListRespDto(
        int currentPage,
        int totalPages,
        List<GetBoardRespDto> boardList
) {
}
