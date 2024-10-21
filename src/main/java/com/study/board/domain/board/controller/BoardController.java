package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.GetBoardService;
import com.study.board.domain.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req, HttpSession session) {
//        User user = (User) session.getAttribute("user");
//
//        if (user == null) {
//            return ResponseEntity.status(401).body("로그인 해주세요");
//        }
        createBoardService.createBoard(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성이 완료되었습니다.");
    }

    // 게시판 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }

}
