package com.javatar.contentservice.repository;

import com.javatar.contentservice.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, String> {
}

