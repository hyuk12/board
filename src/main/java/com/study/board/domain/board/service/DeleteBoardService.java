package com.study.board.domain.board.service;

import com.study.board.domain.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {

private final BoardRepository boardRepository;
    //게시글 삭제
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

}
