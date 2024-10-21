package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.service.*;
import com.study.board.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board", description = "게시판 API")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;
    private final DeleteBoardService deleteBoardService;
    private final UpdateBoardService updateBoardService;
    private final GetBoardPageListService getBoardPageListService;

    // 게시글 생성
    // 로그인한 유저만 게시글 생성이 가능하도록!
    @Operation(summary = "게시판 생성", description = "게시판을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req, HttpSession session) {
        User user = (User) session.getAttribute("user");
        // 세션에 저장된 user가 있는지 확인 (로그인되어있는상태인지)

        if (user == null) {
            // 세션에 유저가 비어있다 => 로그인이 안되어있다.
            return ResponseEntity.status(401).body("로그인 해주세요");
            // 상태코드 401 : unAuthorized (인증/토큰의 키가 잘못되었을 때)
        }
        createBoardService.createBoard(req, user);
        // 로그인이 되어있을때만 createBoard가 가능하다.
        // 세션에 저장되어있는 user의 정보를 가지고 service로 간다

        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성이 완료되었습니다.");
    }

    // 게시글 상세 조회
    // 로그인하지않아도 조회가 가능해야한다 => 수정 불필요
    @Operation(summary = "게시판 상세 조회", description = "게시판의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }

    // 게시글 목록 조회
    // 로그인하지않아도 조회가 가능해야한다 => 수정 불필요
    @Operation(summary = "게시판 목록 조회", description = "게시판 목록 전체를 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok().body(getBoardService.getBoardList());
    }

    // 게시글 삭제
    // 로그인한 유저여야하고,
    // 해당게시글을 작성한 유저와 로그인한 유저가 동일해야 삭제가 가능
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body("로그인 해주세요");
        }

        if (!user.getId().equals(deleteBoardService.getBoardUserId(id))) {
        // 로그인한 유저와 게시글을 작성한 유저가 동일한지 확인
            return ResponseEntity.status(401).body("게시글 작성한 유저와 회원정보가 일치하지 않습니다.");
        }

        deleteBoardService.deleteBoard(id);
        return ResponseEntity.ok().body("삭제 완료");
    }

    // 게시글 수정
    // 로그인한 유저여야하고,
    // 해당게시글을 작성한 유저와 로그인한 유저가 동일해야 삭제가 가능
    @Operation(summary = "게시글 수정", description = "게시글의 제목, 내용을 수정합니다.")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBoard(@RequestBody UpdateBoardReqDto req, @PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body("로그인 해주세요");
        }

        if (!user.getId().equals(deleteBoardService.getBoardUserId(id))) {
            return ResponseEntity.status(401).body("게시글 작성한 유저와 회원정보가 일치하지 않습니다.");
        }

        updateBoardService.updateBoard(req, id);

        return ResponseEntity.ok().body("게시글 수정 완료");
    }

    // 게시글 목록 페이지별 조회
    // 로그인하지않아도 조회가 가능해야한다
    @Operation(summary = "게시판 목록 조회 (pagination)", description = "게시판 목록을 페이지별로 조회합니다.")
    @GetMapping("/pageList")
    public ResponseEntity<?> getBoardPageList(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(getBoardPageListService.getBoardPageList(pageNumber, pageSize));
    }
}
