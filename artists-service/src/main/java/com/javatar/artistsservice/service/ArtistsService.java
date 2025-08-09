package com.javatar.artistsservice.service;

import com.javatar.artistsservice.entity.Artist;
import com.javatar.artistsservice.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistsService {
    private final ArtistRepository repository;

    public ArtistsService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> getArtistByReviewId(Integer reviewId) {
        return repository.findByReviewid(reviewId);
    }
}
