package com.javatar.contentservice.client;

import com.javatar.contentservice.common.TransactionPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "crd-service")
public interface CrdClient {
    @GetMapping("/api/v1/crd/{id}")
    String getCRDData(@PathVariable("id") String id);

    @PostMapping("/saga/create")
    void create(@RequestBody TransactionPayload payload);

    @PostMapping("/saga/compensate")
    void compensate(@RequestBody TransactionPayload payload);
}


