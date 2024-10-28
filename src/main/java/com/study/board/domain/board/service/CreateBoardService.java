package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import com.study.board.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional //서비스 로직에서 필수적임
@Service
@RequiredArgsConstructor
public class CreateBoardService {

    private final BoardRepository boardRepository;
    // 게시판 등록
    public void  createBoard( CreateBoardReqDto req, User user) {
        boardRepository.save(req.of(user));
    }
}
/*
CreateBoardReqDto req: 게시판 생성 요청 정보를 담고 있는 DTO 객체
User user: 게시판을 작성하는 사용자의 정보. 게시판의 작성자 정보를 포함하기 위해 이 매개변수가 필요
req.of(user)는 CreateBoardReqDto 객체를 Board 엔티티로 변환합니다.
게시판의 작성자 정보로 user를 사용하여 게시판 객체를 생성합니다.
boardRepository.save(...) 메서드를 호출하여 변환된 게시판 객체를 데이터베이스에 저장
save() 메서드는 새로운 엔티티 객체를 받아 해당 정보를 데이터베이스에 추가하므로,
 게시글과 작성자 정보가 모두 저장
*/
