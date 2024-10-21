package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    public GetBoardRespDto getBoard(Long id) {
        Board board = boardMapper.getBoard(id);
        Long userId = board.getUserId();
        User byUserId = userMapper.findByUserId(userId);

        return boardMapper.getBoard(id).of(byUserId.getName());
    }

    public List<GetBoardRespDto> getBoardList() {
        return boardMapper.getBoardList().stream()
                .map(Board -> Board.of(userMapper.findByUserId(Board.getUserId()).getName()))
                .toList();
    }
}
