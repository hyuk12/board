package com.study.board.domain.board.entity;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private Long userId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public GetBoardRespDto of(String name) {
        return GetBoardRespDto.builder()
                .title(this.title)
                .content(this.content)
                .author(name)
                .build();
    }
}
