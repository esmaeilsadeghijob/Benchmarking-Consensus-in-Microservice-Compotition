package com.javatar.genresservice.controller;

import com.javatar.genresservice.model.Genre;
import com.javatar.genresservice.repository.GenreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenresController {

    private final GenreRepository genreRepository;

    public GenresController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/{reviewid}")
    public List<Genre> getGenresByReviewId(@PathVariable Integer reviewid) {
        return genreRepository.findByReviewid(reviewid);
    }
}

