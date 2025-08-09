package com.javatar.artistsservice.repository;

import com.javatar.artistsservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByReviewid(Integer reviewid);
}

