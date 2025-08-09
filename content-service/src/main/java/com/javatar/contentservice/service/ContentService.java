package com.javatar.contentservice.service;

import com.javatar.contentservice.model.Content;
import com.javatar.contentservice.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {
    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    public Optional<Content> getContentByReviewId(String reviewid) {
        return repository.findById(reviewid);
    }
}
