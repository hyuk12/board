package com.study.board.domain.user.service;

import com.study.board.domain.user.dto.req.CreateUserReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  //유저 생성
  //매개변수 req는 사용자 생성 요청 정보가 담긴 DTO
  public void createUser(CreateUserReqDto req) {
    userRepository.save(req.of(bCryptPasswordEncoder.encode(req.password())));
  /*req.of()->CreateUserReqDto의 of메서드를 호출해서 사용자의 정보를 새로운 User객체로 변화
  bCryptPasswordEncoder.encode-> 이 비밀번호를 BCrypt 알고리즘을 사용하여 해시화
  userRepository.save-> 생성된 User 객체를 데이터베이스에 저장 ,새로운 사용자가 데이터베이스에 추가되는 과정*/

  }
}

