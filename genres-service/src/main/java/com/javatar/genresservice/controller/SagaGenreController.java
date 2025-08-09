package com.javatar.genresservice.controller;

import com.javatar.genresservice.common.TransactionPayload;
import com.javatar.genresservice.model.Genre;
import com.javatar.genresservice.repository.GenreRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaGenreController {

    private final GenreRepository repository;

    public SagaGenreController(GenreRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody TransactionPayload payload) {
        Genre genre = extractGenre(payload);
        repository.save(genre);
    }

    @PostMapping("/compensate")
    public void compensate(@RequestBody TransactionPayload payload) {
        repository.deleteById(payload.getReviewId());
    }

    private Genre extractGenre(TransactionPayload payload) {
        String name = (String) payload.getData().get("genre");
        return new Genre(payload.getReviewId(), name);
    }
}
