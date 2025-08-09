package com.javatar.contentservice.client;

import com.javatar.contentservice.model.TransactionPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "genres-service")
public interface ParticipantClient {
    @PostMapping("/2pc/prepare")
    boolean prepare(@RequestBody TransactionPayload payload);

    @PostMapping("/2pc/commit")
    void commit(@RequestBody TransactionPayload payload);

    @PostMapping("/2pc/rollback")
    void rollback(@RequestBody TransactionPayload payload);
}
