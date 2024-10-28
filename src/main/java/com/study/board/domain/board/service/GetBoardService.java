package com.study.board.domain.board.service;

import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.entity.repository.BoardRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {

    private final BoardRepository boardRepository;

    //특정 게시글을 조회하는 메서드로, ID가 존재하지 않을 경우 예외를 던짐
    public GetBoardRespDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ID가 존재하지 않습니다."));
        return GetBoardRespDto.from(board);
    }

    //페이지 정보를 기반으로 게시글 목록을 반환하며, findAllByOrderByCreatedDateDesc(pageable)를 통해 생성일자 기준으로 내림차순 정렬하여 조회
    // map(GetBoardRespDto::from)으로 엔티티를 DTO로 변환
        public Page<GetBoardRespDto> getBoardList(Pageable pageable) {
            return boardRepository.findAllBoards(pageable)
                .map(GetBoardRespDto::from);
        }



    public List<Board> getBoardListSorted(){
        return boardRepository.findAll(Sort.by(Direction.ASC, "title"));
        //제목 기준으로 오름차순으로 정렬된 게시글 목록을 가져옵니다.
        // 그러나 페이지네이션이 적용되지 않아 전체 데이터를 불러오게 됨.
    }
    }
