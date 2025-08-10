package com.javatar.raftnodeservice.service;

import com.javatar.raftnodeservice.common.Block;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RaftState {
    private int currentTerm = 0;
    private String votedFor = null;
    private List<Block> log = new ArrayList<>();
    private int commitIndex = -1;

    public synchronized boolean appendEntry(Block block) {
        log.add(block);
        commitIndex++;
        return true;
    }

    public synchronized boolean requestVote(int term, String candidateId) {
        if (term > currentTerm) {
            currentTerm = term;
            votedFor = candidateId;
            return true;
        }
        return false;
    }

    public List<Block> getCommittedBlocks() {
        return log.subList(0, commitIndex + 1);
    }
}
