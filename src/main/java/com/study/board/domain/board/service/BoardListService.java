package com.study.board.domain.board.service;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardListService {
  private final BoardRepository boardRepository;

  public Page<Board> getBoardByUsername(String username, Pageable pageable){
    return boardRepository.findByUserUsername(username, pageable);
  }

}
