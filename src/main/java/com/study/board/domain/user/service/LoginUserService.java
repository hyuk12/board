package com.study.board.domain.user.service;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.dto.req.LoginUserReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.user.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void loginUser(LoginUserReqDto reqDto, HttpSession session) {
        User byUsername = userMapper.findByUsername(reqDto.username());

        if (byUsername == null || !passwordEncoder.matches(reqDto.rawPassword(), byUsername.getPassword())) {
            throw new RuntimeException("로그인 실패");
        }

        session.setAttribute("user", byUsername);
    }
}
