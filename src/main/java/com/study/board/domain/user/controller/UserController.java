package com.study.board.domain.user.controller;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.dto.req.LoginUserReqDto;
import com.study.board.domain.user.service.CreateUserService;
import com.study.board.domain.user.service.LoginUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 API")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final CreateUserService createUserService;
    private final LoginUserService loginUserService;

    @Operation(summary = "유저 생성", description = "유저를 생성합니다.")
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserReqDto reqDto) {
        try {
            createUserService.createUser(reqDto);
            return ResponseEntity.ok().body("유저 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "로그인", description = "로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserReqDto reqDto, HttpSession session) {
        try {
            loginUserService.loginUser(reqDto, session);
            return ResponseEntity.ok("Login Success");
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @Operation(summary = "로그아웃", description = "로그아웃합니다.")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
