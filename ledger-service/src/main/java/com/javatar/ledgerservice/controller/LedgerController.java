package com.javatar.ledgerservice.controller;

import com.javatar.ledgerservice.common.Block;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    private final List<Block> blockchain = new ArrayList<>();

    @PostMapping("/commit")
    public ResponseEntity<?> commitBlock(@RequestBody Block block) {
        blockchain.add(block);
        return ResponseEntity.ok("Block committed");
    }

    @GetMapping("/chain")
    public List<Block> getChain() {
        return blockchain;
    }
}

