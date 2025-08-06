package com.javatar.contentservice.controller;

import com.javatar.contentservice.client.CRDClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/content")
public class ContentController {

    private final CRDClient crdClient;

    public ContentController(CRDClient crdClient) {
        this.crdClient = crdClient;
    }

    @GetMapping("/{id}")
    public String getContent(@PathVariable String id) {
        String crdData = crdClient.getCRDData(id);
        return "Content Head → " + crdData;
    }
}
