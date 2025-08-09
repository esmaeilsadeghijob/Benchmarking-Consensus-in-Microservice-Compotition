package com.javatar.artistsservice.controller;

import com.javatar.artistsservice.entity.Artist;
import com.javatar.artistsservice.service.ArtistsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/artists")
public class ArtistsController {

    private final ArtistsService artistsService;

    @GetMapping("/{reviewid}")
    public List<Artist> getArtistsByReviewId(@PathVariable Integer reviewid) {
        return artistsService.getArtistByReviewId(reviewid);
    }
}


