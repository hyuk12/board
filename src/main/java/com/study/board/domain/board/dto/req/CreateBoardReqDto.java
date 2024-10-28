package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import com.study.board.domain.user.entity.User;
import java.time.LocalDateTime;

// java 15 이후 나온 클래스, 최소한의 정보만 담음
public record CreateBoardReqDto(
        String title,
        String content
) {
    public Board of(User user) {// 클라이언트로부터 받아온 정보로 board 테이블에 행 추가 함을 위함.
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
/*CreateBoardReqDto에서 게시판을 생성하기 위해 of 메서드를 호출함으로써,
 클라이언트가 보낸 데이터(title, content)와 함께 게시판 작성자 정보(user)를
 결합하여 Board 엔티티를 생성*/
