package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    public GetBoardRespDto getBoard(Long id) {
        Board board = boardMapper.getBoard(id);
        Long userId = board.getUserId();
        User byUserId = userMapper.findByUserId(userId);

        return boardMapper.getBoard(id).of(byUserId.getName());
    }
    public List<GetBoardRespDto> getBoardList(){
        List<Board> boards = boardMapper.getBoardList();
        return boards.stream()
                .map(board -> board.of(getAuthorName(board.getUserId())))// userId로 author 이름을 가져와서 전달
                .toList();
    }
    // userId로부터 사용자 이름을 가져오는 메서드
    // 예시: userId로부터 사용자 이름을 가져오는 메서드
    private String getAuthorName(Long userId) {
        User user = userMapper.findByUserId(userId); // userId로 User 객체를 조회
        return (user != null) ? user.getUsername() : "Unknown";
        // user가 null이 아닐 때 username 반환, null이면 "Unknown"
    }
}
