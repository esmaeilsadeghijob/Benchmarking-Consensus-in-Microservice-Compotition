package com.javatar.contentservice.client;

import com.javatar.contentservice.common.TransactionPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "genre-service")
public interface GenreClient {

    @PostMapping("/saga/create")
    void create(@RequestBody TransactionPayload payload);

    @PostMapping("/saga/compensate")
    void compensate(@RequestBody TransactionPayload payload);
}
