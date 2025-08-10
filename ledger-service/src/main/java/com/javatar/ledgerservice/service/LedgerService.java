package com.javatar.ledgerservice.service;

import com.javatar.ledgerservice.common.Block;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LedgerService {
    private final List<Block> blockchain = new ArrayList<>();

    public void commit(Block block) {
        blockchain.add(block);
    }

    public List<Block> getChain() {
        return blockchain;
    }
}
