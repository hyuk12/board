/*package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardListPaginationRespDto;
import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardListPaginationService {
 *//* private final BoardRepository boardRepository;

  public GetBoardListPaginationRespDto getBoardListPagination(int pageNumber, int pageSize) {
    if (pageNumber < 1) {
      pageNumber = 1;
    }

    int currentPage = pageNumber;
    Pageable pageable = PageRequest.of(currentPage - 1, pageSize); // Pageable 객체 생성
    Page<Board> boardPage = boardRepository.findAll(pageable); // 모든 게시글 조회

    List<GetBoardRespDto> boardList = boardPage.getContent().stream()
        .map(GetBoardRespDto::of) // GetBoardRespDto로 변환
        .collect(Collectors.toList());

    return GetBoardListPaginationRespDto.builder()
        .currentPage(currentPage)
        .totalPage(boardPage.getTotalPages())
        .boardList(boardList)
        .build();
  }*//*
}*/


