package com.javatar.contentservice.controller;

import com.javatar.contentservice.client.ParticipantClient;
import com.javatar.contentservice.model.TransactionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionCoordinator {

    private final List<ParticipantClient> participants;

    public TransactionCoordinator(List<ParticipantClient> participants) {
        this.participants = participants;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTransaction(@RequestBody TransactionPayload payload) {
        boolean allPrepared = participants.stream()
                .allMatch(p -> p.prepare(payload));

        if (!allPrepared) {
            participants.forEach(p -> p.rollback(payload));
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Transaction rolled back");
        }

        participants.forEach(p -> p.commit(payload));
        return ResponseEntity.ok("Transaction committed");
    }
}
