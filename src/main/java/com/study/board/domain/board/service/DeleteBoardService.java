package com.study.board.domain.board.service;

import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {

    private final BoardMapper boardMapper;

    public Long getBoardUserId(Long boardId) {
        return boardMapper.getBoardUserId(boardId);
    }

    public void deleteBoard(Long id) {
        boardMapper.deleteBoard(id);
    }
}
