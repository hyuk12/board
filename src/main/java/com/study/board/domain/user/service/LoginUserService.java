package com.study.board.domain.user.service;


import com.study.board.domain.user.dto.req.LoginReqDto;
import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.entity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  //로그인 기능
  public void loginUser(LoginReqDto req, HttpSession session){
    User byUsername = userRepository.findByUsername(req.username());


    //username이 없으면 로그인 실패
    //matches 메서드는 입력된 비밀번호를 해시화하여 데이터베이스에 저장된 비밀번호와 비교.
    // 이 두 값이 일치하면 **true**를 반환하고, 그렇지 않으면 **false**를 반환
    if(byUsername == null || !passwordEncoder.matches(req.rawPassword(), byUsername.getPassword())){
        throw new RuntimeException("로그인 실패");
    }
    session.setAttribute("user", byUsername);//로그인이 실패하지 않았으면 세션에 정보를 저장한다.
    }
  }


