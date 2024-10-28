package com.study.board.domain.board.dto.resp;

import com.study.board.domain.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
    Long id,
    String title,
    String content,
    String author,
    LocalDateTime createdDate,
    LocalDateTime updatedDate,
    String message // 성공 메시지 추가
    // 결과로 보여줄 필드
) {

  /*
 Static Factory Method: from(Board board) 메서드는
 Board 엔티티를 DTO로 변환하는 유틸리티 메서드
   */
  public static GetBoardRespDto from (Board board) {
    return GetBoardRespDto.builder()
        .id(board.getId())
        .title(board.getTitle())
        .content(board.getContent())
        .author(board.getUser().getUsername())
        //실제 작성자의 username을 가져옴
        //Board 엔티티와 User 엔티티는 user_id를 통해 참조 관계.
        // 이를 통해 Board 객체가 User 객체의 정보를 접근할 수 있다
        .createdDate(board.getCreatedDate())
        .updatedDate(board.getUpdatedDate())
        .message("게시글 조회에 성공했습니다.") // 기본 메시지 설정
        .build();
  }
}
/*
* dto클래스는 게시글의 정보를 담아 클라이언트에 반환할 때 사용되는 클래서
* 제목, 내용, 작성자, 작성 날짜, 수정 날짜 등 클라이언트에게 필요한 데이터만 포함
* from 메서드 보드 객체의 각 필드 값을 GetBoardRespDto로 매핑한다
* Board 엔티티 객체를 GetBoardRespDto로 변환해주는 메서드
* Board엔티티에서 필요한 데이터를 추출하여 DTO객체로 매핑
* */