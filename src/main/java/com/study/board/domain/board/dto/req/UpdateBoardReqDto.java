package com.study.board.domain.board.dto.req;

import com.study.board.domain.board.entity.Board;

import java.time.LocalDateTime;
//게시글을 수정할 때 필요한 제목, 내용을 담는다.
//사용자가 게시글 수정 요청을 보낼 때, 이 DTO에 수정된 정보를 담아서 서버로 전송

public record UpdateBoardReqDto(
    String title,
    String content) {

    public Board of(){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
