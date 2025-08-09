package com.javatar.genresservice.controller;

import com.javatar.genresservice.model.Genre;
import com.javatar.genresservice.repository.GenreRepository;
import com.javatar.genresservice.common.TransactionPayload;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/2pc")
public class TwoPhaseController {

    private final GenreRepository repository;
    private final Map<String, Genre> tempStore = new HashMap<>();

    public TwoPhaseController(GenreRepository repository) {
        this.repository = repository;
    }

    /**
     * Phase 1: Prepare
     * Validate and temporarily store the data.
     */
    @PostMapping("/prepare")
    public boolean prepare(@RequestBody TransactionPayload payload) {
        try {
            Genre genre = extractGenre(payload);
            tempStore.put(payload.getReviewId(), genre);
            System.out.println("✅ Prepared: " + genre);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Prepare failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Phase 2: Commit
     * Persist the data permanently.
     */
    @PostMapping("/commit")
    public void commit(@RequestBody TransactionPayload payload) {
        Genre genre = tempStore.remove(payload.getReviewId());
        if (genre != null) {
            repository.save(genre);
            System.out.println("✅ Committed: " + genre);
        } else {
            System.err.println("⚠️ Nothing to commit for reviewId: " + payload.getReviewId());
        }
    }

    /**
     * Phase 2: Rollback
     * Discard the temporary data.
     */
    @PostMapping("/rollback")
    public void rollback(@RequestBody TransactionPayload payload) {
        Genre removed = tempStore.remove(payload.getReviewId());
        System.out.println("🗑 Rolled back: " + removed);
    }

    /**
     * Helper method to extract Genre from payload.
     */
    private Genre extractGenre(TransactionPayload payload) {
        String genreName = (String) payload.getData().get("genre");
        return new Genre(payload.getReviewId(), genreName);
    }
}
