package com.javatar.contentservice.controller;

import com.javatar.contentservice.model.Content;
import com.javatar.contentservice.service.ContentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService service;

    public ContentController(ContentService service) {
        this.service = service;
    }

    @GetMapping("/{reviewid}")
    public Content getContent(@PathVariable String reviewid) {
        return service.getContentByReviewId(reviewid)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }
}

