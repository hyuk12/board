package com.study.board.domain.board.entity.repository;

import com.study.board.domain.board.entity.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByOrderByCreatedDateDesc(Pageable pageable);

    // fetch join
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.id = :id")
    Board findBoardWithUser(@Param("id") Long id);

}
