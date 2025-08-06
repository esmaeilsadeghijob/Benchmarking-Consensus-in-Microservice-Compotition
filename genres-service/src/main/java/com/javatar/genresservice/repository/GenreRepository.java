package com.javatar.genresservice.repository;


import com.javatar.genresservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByReviewid(Integer reviewid);
}

