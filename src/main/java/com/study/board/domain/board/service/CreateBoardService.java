package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 디폴트 값이 서비스 로직이 정상적으로 완료되지않으면 디비에 적용이 되지않습니다.
@Transactional
@Service
@RequiredArgsConstructor
public class CreateBoardService {
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    // 게시판 등록
    public void createBoard( User user,CreateBoardReqDto req) {
        boardMapper.createBoard(req.of(user.getId()));
    }
}
