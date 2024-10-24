package com.study.board.domain.user.entity.repository;

import com.study.board.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByName(String name);

  List<User> findByAgeGreaterThan(int age);

  List<User> findByNameAndAge(String name, int age);

  @Query("SELECT u FROM User u WHERE u.name = :name")
  List<User> findUserByName(@Param("name") String name);

  @Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
  List<User> findUsersByAgeGreaterThan(@Param("age") int age);

  User findByUsername(String username);
}
