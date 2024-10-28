package com.study.board.domain.board.dto.resp;
import lombok.Builder;

import java.util.List;
@Builder
public record GetBoardListPaginationRespDto(
    int currentPage,
    int totalPage,
    List<GetBoardRespDto> boardList
) {

}