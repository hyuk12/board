package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardPageListRespDto;
import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardPageListService {

    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    public GetBoardPageListRespDto getBoardPageList(int pageNumber, int pageSize) {

        int currentPage = pageNumber;
        int offset = (currentPage - 1) * pageSize;
        int totalPosts = boardMapper.getTotalPosts();
        int totalPages;
        if (totalPosts % pageSize == 0) {
            totalPages = totalPosts / pageSize;
        }
        else {
            totalPages = (totalPosts / pageSize) + 1;
        }

        List<Board> board = boardMapper.getBoardPageList(pageSize, offset);

        List<GetBoardRespDto> boardList = board.stream()
                .map(Board -> Board.of(userMapper.findByUserId(Board.getUserId()).getName()))
                .toList();

        return GetBoardPageListRespDto.builder()
                .currentPage(currentPage)
                .totalPages(totalPages)
                .boardList(boardList)
                .build();
    }
}
