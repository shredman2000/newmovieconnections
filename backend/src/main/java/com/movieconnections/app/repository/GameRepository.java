package com.movieconnections.app.repository;

import com.movieconnections.app.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByDateToGoLive(LocalDate dateToGoLive);
    Optional<Game> findByDateToGoLive(LocalDate dateToGoLive);

}
