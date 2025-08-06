package com.javatar.contentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crd-service")
public interface CRDClient {
    @GetMapping("/api/v1/crd/{id}")
    String getCRDData(@PathVariable("id") String id);
}
