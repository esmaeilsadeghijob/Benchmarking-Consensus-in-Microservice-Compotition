package com.javatar.contentservice.controller;

import com.javatar.contentservice.common.TransactionPayload;
import com.javatar.contentservice.service.SagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaOrchestrator {

    private final SagaService sagaService;

    public SagaOrchestrator(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSaga(@RequestBody TransactionPayload payload) {
        boolean success = sagaService.executeSaga(payload);
        return success ? ResponseEntity.ok("Saga completed") :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Saga failed and rolled back");
    }
}
