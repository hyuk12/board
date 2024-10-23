package com.study.board.domain.board.service;

import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    //게시글 삭제
    public void deleteBoard(Long id){
        boardMapper.deleteBoard(id);
    }
    //유저 아이디와 글 쓴 사람 일치 하는 지 확인
    public Long comfirmId(Long id){ //id를 받아야 게시글 작성자 id(userid)를 확인할 수 있음

        return boardMapper.confirmId(id);
    }
}
