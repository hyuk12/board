package com.study.board.domain.user.entity.repository;

import com.study.board.domain.board.entity.Board;
import com.study.board.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  //username(=아이디)로 유저를 찾는 메서드
  //User findByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.username = :username")
  User findByUsername(@Param("username")String username);
  //WHERE u.name = :name은 메서드에서 주어진 이름(:name)과 같은지를 확인하겠다"는 의미
  //특정 이름을 가진 사용자를 찾기 위한 조건으로 사용

  /*@Param("username")은 이 메서드가 호출될 때 username이라는 이름으로 매개변수를 받아야 한다는 것을 나타냅니다.
    String username은 매개변수의 타입이 String임을 의미합니다. 이 username은 실제로 메서드가 호출될 때
    전달되는 값으로, 데이터베이스에서 검색할 아이디(username) 됩니다.*/




}
