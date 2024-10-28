package com.study.board.domain.board.controller;

import com.study.board.domain.board.dto.req.CreateBoardReqDto;
import com.study.board.domain.board.dto.req.UpdateBoardReqDto;
import com.study.board.domain.board.dto.resp.GetBoardRespDto;
import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardListService;
import com.study.board.domain.board.service.CreateBoardService;
import com.study.board.domain.board.service.DeleteBoardService;
import com.study.board.domain.board.service.GetBoardService;
import com.study.board.domain.board.service.UpdateBoardService;
import com.study.board.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Tag(name = "Board", description = "게시판 API")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;
    private final DeleteBoardService deleteBoardService;
    private final UpdateBoardService updateBoardService;
    private final BoardListService boardListService;


    // 게시글 생성
    @Operation(summary = "게시판 생성", description = "게시판을 생성합니다.")
    @PostMapping
    public ResponseEntity<String> createBoard(@RequestBody CreateBoardReqDto req,
        HttpSession session) {

        try {
            User user = (User) session.getAttribute("user");

            if (user == null) {
                return ResponseEntity.status(401).body("로그인 해주세요");
            }

            createBoardService.createBoard(req, user);
            return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성 완료");

        } catch (Exception e) {
            // 기타 예외 처리
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "게시판 생성 중 오류가 발생했습니다."); // 상태 코드 500
        }

    }

    // 게시글 상세 조회
    @Operation(summary = "게시판 상세 조회", description = "게시판의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<GetBoardRespDto> getBoard(@PathVariable Long id) {
        try {
            GetBoardRespDto response = getBoardService.getBoard(id);
            return ResponseEntity.ok(response); // 정상 조회 시 상태 코드 200
        } catch (EntityNotFoundException e) {
            // 게시글을 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GetBoardRespDto.builder()
                    .message("게시글을 찾을 수 없습니다.") // 에러 메시지 추가
                    .build());
        } catch (Exception e) {
            // 기타 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GetBoardRespDto.builder()
                    .message("게시글 조회 중 오류가 발생했습니다.") // 에러 메시지 추가
                    .build());
        }
    }


    // 게시글 목록 조회(페이지네이션)
    @Operation(summary = "게시판 목록 조회", description = "게시판의 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<GetBoardRespDto>> getBoardList(
        @RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,
            pageSize); //PageRequest.of(pageNo, pageSize)를 통해 요청된 페이지 정보를 바탕으로 Pageable 객체를 생성
        Page<GetBoardRespDto> listResponse = getBoardService.getBoardList(pageable);

        return ResponseEntity.ok(listResponse.getContent());
        //listResponse.getContent()로 실제 데이터 목록을 반환
    }


    //게시글 삭제
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
    @DeleteMapping("/delete/{id}")//board의 아이디
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(401).body("로그인 해주세요");
            }
            deleteBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시판이 삭제되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("게시판을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("게시판 삭제 중 오류가 발생했습니다.");
        }
    }


    //게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/update/{id}") // board의 아이디를 입력받음
    public ResponseEntity<String> updateBoard(@PathVariable Long id,
        @RequestBody UpdateBoardReqDto req, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");

            if (user == null) {
                return ResponseEntity.status(401).body("로그인 해주세요");
            }

                updateBoardService.boardUpdate(id, req, user);
                return ResponseEntity.ok().body("게시글 수정 완료!");
            } catch(Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @Operation(summary = "사용자의 게시물 목록 페이지네이션 조회")
    @GetMapping("/user")
    public ResponseEntity<Page<Board>> getBoardsByUsername(
        @RequestParam String username,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // 페이지 요청 생성
        Page<Board> boards = boardListService.getBoardByUsername(username, pageable);
        return ResponseEntity.ok(boards); // 페이지네이션된 결과 반환
    }

    }
