package com.study.board.domain.user.service;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.global.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createUser(CreateUserReqDto reqDto) {
        User user = reqDto.of(passwordEncoder.encode(reqDto.rawPassword()));
        log.info("user id: {}", user.getName());
        userMapper.createUser(user);
    }
}
