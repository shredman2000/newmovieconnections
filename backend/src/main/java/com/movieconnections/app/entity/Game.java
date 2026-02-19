package com.movieconnections.app.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private LocalDate dateToGoLive;

    private String movie1title;
    @ElementCollection
    private List<String> movie1Clues = new ArrayList<>();

    private String movie2title;
    @ElementCollection
    private List<String> movie2Clues = new ArrayList<>();

    private String movie3title; 
    @ElementCollection

    private List<String> movie3Clues = new ArrayList<>();
    private String movie4title;

    @ElementCollection
    private List<String> movie4Clues = new ArrayList<>();

    public Game() {}

    public Game(LocalDate dateToGoLive, String movie1title, String movie2title, String movie3title, String movie4title, List<String> movie1Clues, List<String> movie2Clues, List<String> movie3Clues,List<String> movie4Clues ) {
        this.dateToGoLive = dateToGoLive;
        this.movie1title = movie1title;
        this.movie2title = movie2title;
        this.movie3title = movie3title;
        this.movie4title = movie4title;
        this.movie1Clues = movie1Clues;
        this.movie2Clues = movie2Clues;
        this.movie3Clues = movie3Clues;
        this.movie4Clues = movie4Clues;
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

    public String getMovie1title() {
        return movie1title;
    }

    public void setMovie1title(String movie1title) {
        this.movie1title = movie1title;
    }

    public List<String> getMovie1Clues() {
        return movie1Clues;
    }

    public void setMovie1Clues(List<String> movie1Clues) {
        this.movie1Clues = movie1Clues;
    }

    public String getMovie2title() {
        return movie2title;
    }

    public void setMovie2title(String movie2title) {
        this.movie2title = movie2title;
    }

    public List<String> getMovie2Clues() {
        return movie2Clues;
    }

    public void setMovie2Clues(List<String> movie2Clues) {
        this.movie2Clues = movie2Clues;
    }

    public String getMovie3title() {
        return movie3title;
    }

    public void setMovie3title(String movie3title) {
        this.movie3title = movie3title;
    }

    public List<String> getMovie3Clues() {
        return movie3Clues;
    }

    public void setMovie3Clues(List<String> movie3Clues) {
        this.movie3Clues = movie3Clues;
    }

    public String getMovie4title() {
        return movie4title;
    }

    public void setMovie4title(String movie4title) {
        this.movie4title = movie4title;
    }

    public List<String> getMovie4Clues() {
        return movie4Clues;
    }

    public void setMovie4Clues(List<String> movie4Clues) {
        this.movie4Clues = movie4Clues;
    }




}
