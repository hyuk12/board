package com.study.board.domain.user.controller;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.dto.req.LoginUserReqDto;
import com.study.board.domain.user.service.CreateUserService;
import com.study.board.domain.user.service.GetUserService;
import com.study.board.domain.user.service.LoginUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final LoginUserService loginUserService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserReqDto reqDto) {
        try {
            createUserService.createUser(reqDto);
            return ResponseEntity.ok().body("유저 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserReqDto reqDto, HttpSession session) {
        try {
            loginUserService.loginUser(reqDto, session);
            return ResponseEntity.ok("Login Success");
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserList(@RequestParam String name) {
        return ResponseEntity.ok().body(getUserService.findByName(name));
    }

    @GetMapping("/user/query")
    public ResponseEntity<?> getQueryUserList(@RequestParam String name) {
        return ResponseEntity.ok().body(getUserService.findUserByName(name));
    }
}
