package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import com.study.board.domain.user.entity.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateBoardService {
    private final BoardRepository boardRepository;

      //게시글 수정
      //게시글의 고유번호인 id와 dto를 매개변수로 받아서
      public void boardUpdate(Long id, UpdateBoardReqDto req, User user){
        // 1. 주어진 ID로 게시글을 데이터베이스에서 찾는다.
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ID로 찾으려는 게시글이 존재하지 않습니다."));

        // 2. 수정된 제목과 내용을 Board 객체에 적용한다.
        board.update(req.title(), req.content()); // 사용자가 수정 한 내용을 update메서드를 이용해 수정한다.
    }

}
/*중요!헷갈렷던 점
게시글 생성 시 User 정보는 dto를 통해 직접 설정되지만, 수정 시에는 서비스 로직에서 권한 확인을 위해
사용된다. 그러므로 게시글 수정 시 user정보는 서비스 로직에서 매개변수로 받아와 권환 확인을 위해 사용됨.
게시글 수정 시 update 메서드를 사용하는 이유는 수정할 정보를 DTO로 받아와서 기존 게시글의 상태를 직접 변경

*/