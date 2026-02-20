package com.movieconnections.app.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Clue> clues = new ArrayList<>();
    

    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void addClue(Clue clue) {
        clues.add(clue);
        clue.setMovie(this);
    }
    public List<Clue> getClues() {
        return this.clues;
    }

    public Game getGame() {
        return this.game;
    }
}
