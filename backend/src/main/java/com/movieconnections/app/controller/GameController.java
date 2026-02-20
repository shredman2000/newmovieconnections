package com.movieconnections.app.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

import com.movieconnections.app.entity.Clue;
import com.movieconnections.app.entity.Game;
import com.movieconnections.app.entity.Movie;
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
    public ResponseEntity<String> createGame(@RequestBody Map<String, Object> request)  {
        String dateStr = (String) request.get("goLiveDate");

        LocalDate goLiveDate = LocalDate.parse(dateStr);

        if (gameRepository.existsByDateToGoLive(goLiveDate)) {
            return ResponseEntity.badRequest().body("A game is already scheduled for that date");
        }

        Game game = new Game();
        game.setDateToGoLive(goLiveDate);

        for (int i = 0; i < 4; i++) {
            String title = (String) request.get("title" + i);
            @SuppressWarnings("unchecked")
            List<String> clueUrls = (List<String>) request.get("clues" + i);
            
            Movie movie = new Movie(title);
        
            for (String url : clueUrls) {
                Clue clue = new Clue(url);

                movie.addClue(clue);
            }
            game.addMovie(movie);
        }

        gameRepository.save(game);

        return ResponseEntity.ok("Game Created");

    }

    @PostMapping("/getGame")
    public ResponseEntity<Game> getGame(@RequestBody Map<String,String> request) {
        System.out.println(request);
        String dateStr = request.get("date");
        LocalDate date = LocalDate.parse(dateStr);

        Optional<Game> gameOpt = gameRepository.findByDateToGoLive(date);

        if (gameOpt.isEmpty()) { 
            return ResponseEntity.badRequest().body(null);
        }


        Game game = gameOpt.get();

        return ResponseEntity.ok(game);
    }
}
