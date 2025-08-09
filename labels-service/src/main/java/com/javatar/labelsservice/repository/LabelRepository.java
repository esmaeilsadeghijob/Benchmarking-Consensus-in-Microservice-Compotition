package com.javatar.labelsservice.repository;

import com.javatar.labelsservice.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Integer> {
    List<Label> findByReviewid(Integer reviewid);
}
