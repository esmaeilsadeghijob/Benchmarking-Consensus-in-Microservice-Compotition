package com.javatar.crdservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crd")
public class CrdController {

    @GetMapping("/{id}")
    public String getCRDData(@PathVariable String id) {
        return "CRD Data for content ID: " + id;
    }
}
