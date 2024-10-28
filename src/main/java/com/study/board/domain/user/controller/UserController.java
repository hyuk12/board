package com.study.board.domain.user.controller;


import com.study.board.domain.board.entity.Board;
import com.study.board.domain.board.service.BoardListService;
import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.dto.req.LoginReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.service.CreateUserService;
import com.study.board.domain.user.service.LoginUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final CreateUserService createUserService;
  private final LoginUserService loginUserService;
  private final BoardListService boardListService;


  @Operation(summary = "회원 가입", description = "유저 생성")
  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody CreateUserReqDto req) {
    try {
      createUserService.createUser(req);
      return ResponseEntity.ok().body("유저 생성이 완료되었습니다.");
    } catch (IllegalArgumentException e) {
      // 잘못된 요청(400 Bad Request)
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 요청입니다.");
    } catch (Exception e) {
      // 서버 오류(500 Internal Server Error)
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
  }
  /*새로운 User 객체는 서비스 레이어에서 생성되고 데이터베이스에 저장되며,
   컨트롤러는 이 객체를 반환받지 않음. 대신, 서비스 호출 후 성공 메시지나 오류 메시지를 클라이언트에 응답으로 전달*/

  @Operation(summary = "로그인", description = "유저 로그인")
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginReqDto req, HttpSession session) {
    try {
      loginUserService.loginUser(req, session);
      return ResponseEntity.ok("로그인 성공");
    } catch (IllegalArgumentException e) {
      // 인증 실패
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
    } catch (Exception e) {
      // 서버 오류
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
  }

  @Operation(summary = "로그아웃", description = "유저 로그아웃")
  @PostMapping("/logout")
  public ResponseEntity<String> logoutUser(HttpSession session) {
    try {
      session.removeAttribute("user");
      return ResponseEntity.ok().body("로그아웃 완료");
    } catch (Exception e) {
      // 서버 오류
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
  }




}
