package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import java.time.LocalDateTime;
//게시글을 수정할 때 필요한 제목, 내용을 담는다.
//사용자가 게시글 수정 요청을 보낼 때, 이 DTO에 수정된 정보를 담아서 서버로 전송

public record UpdateBoardReqDto(String title,
                                String content) {
//DTO를 엔티티(Board)**로 변환하는 역할.
// 즉, 사용자가 수정한 title과 content 값을 Board 엔티티 객체로 만들어주는 메서드
    public Board of(){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
