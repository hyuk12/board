package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.global.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateBoardService {

    private final BoardMapper boardMapper;

    public void updateBoard(UpdateBoardReqDto req, Long id) {
        Board upBoard = req.of();
        upBoard.setId(id);
        boardMapper.updateBoard(upBoard);
    }
}
