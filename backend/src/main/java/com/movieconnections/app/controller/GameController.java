package com.movieconnections.app.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;

import com.movieconnections.app.entity.Game;
import com.movieconnections.app.repository.GameRepository;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {
    
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public String getTest() {
        return "GET WORKS";
    }

    @PostMapping("/create")
    public ResponseEntity<Game> createGame(@RequestBody Map<String, Object> request)  {


        Game game = new Game();

        for (int i = 0; i < 4; i++) {
            String title = (String) request.get("title" + i);
            @SuppressWarnings("unchecked")
            List<String> clueUrls = (List<String>) request.get("clues" + i);


            switch (i) {
                case 0: 
                    game.setMovie1title(title);
                    game.setMovie1Clues(clueUrls);
                    break;
                case 1:
                    game.setMovie2title(title);
                    game.setMovie2Clues(clueUrls);
                    break; 
                case 2:
                    game.setMovie3title(title);
                    game.setMovie3Clues(clueUrls); 
                    break;
                case 3:
                    game.setMovie4title(title);
                    game.setMovie4Clues(clueUrls); 
                    break;
                }
        }

        gameRepository.save(game);

        return ResponseEntity.ok(game);

    }
}
