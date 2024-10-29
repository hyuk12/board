package com.study.board.domain.user.service;

import com.study.board.domain.user.entity.User;
import com.study.board.domain.user.entity.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService {
  private final UserRepository userRepository;

  public List<User> findByName(String name) {
    return userRepository.findByName(name);
  }

  public List<User> findByAgeGreaterThan(int age) {
    return userRepository.findByAgeGreaterThan(age);
  }

  public List<User> findByNameAndAge(String name, int age) {
    return userRepository.findByNameAndAge(name, age);
  }

  public List<User> findUserByName(String name) {
    return userRepository.findUserByName(name);
  }

  public List<User> findUsersByAgeGreaterThan(int age) {
    return userRepository.findUsersByAgeGreaterThan(age);
  }
}
