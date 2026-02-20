package com.movieconnections.app.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private LocalDate dateToGoLive;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();



    public Game() {}

    public Game(LocalDate dateToGoLive) {
        this.dateToGoLive = dateToGoLive;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public LocalDate getDateToGoLive() {
        return dateToGoLive;
    }

    public void setDateToGoLive(LocalDate dateToGoLive) {
        this.dateToGoLive = dateToGoLive;
    }
    

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.setGame(this);
    }
    public List<Movie> getMovies() {
        return this.movies;
    }


}
