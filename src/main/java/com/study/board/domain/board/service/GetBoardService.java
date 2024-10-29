package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.board.BoardMapper;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {
//    private final BoardMapper boardMapper;
//    private final UserMapper userMapper;
    private final BoardRepository boardRepository;

    public GetBoardRespDto getBoard(Long id) {
//        Board board = boardMapper.getBoard(id);
//        Long userId = board.getUserId();
//        User byUserId = userMapper.findByUserId(userId);
        Board byId = boardRepository.findById(id).orElse(null);
        if (byId == null) {
            throw new IllegalArgumentException("데이터가 없습니다");
        }
        return byId.of();
    }

    public Page<?> getBoardListPageNation(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return boardRepository.findAllByOrderByCreatedDateDesc(pageable).map(GetBoardRespDto::from);
    }

    public List<Board> getBoardListSorted() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "title"));
    }

    public Board findBoardWithUser(Long id) {
        return boardRepository.findBoardWithUser(id);
    }

//    public List<Board> findAllWithUser() {
//        return boardRepository.findAllWithUser();
//    }
}
