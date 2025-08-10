package com.javatar.aggregationservice.service;

import com.javatar.aggregationservice.model.Block;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BlockSenderService {

    private final WebClient.Builder webClientBuilder;

    public BlockSenderService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public void sendBlock(Block block) {
        webClientBuilder.build()
                .post()
                .uri("http://raft-node-service/api/v1/raft/append")
                .bodyValue(block)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(); // fire-and-forget
    }
}

