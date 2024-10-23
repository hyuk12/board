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

    //게시글 수정
    //게시글의 고유번호인 id와 dto를 매개변수로 받아서
      public void boardUpdate(Long id, UpdateBoardReqDto req){
        Board boardUpdate = req.of();  //Dto 안에 있는 제목과 내용을 이용해 Board 엔티티 객체를 생성
          boardUpdate.setId(id);//수정하려는 **게시글의 고유 번호(id)**를 Board 객체에 설정
        boardMapper.updateBoard(boardUpdate);//BoardMapper 인터페이스를 이용해, 변환된 Board 객체를 DB에 업데이트
    }

}
