package com.javatar.raftnodeservice.controller;

import com.javatar.raftnodeservice.common.Block;
import com.javatar.raftnodeservice.service.RaftState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raft")
public class RaftController {

    @Autowired
    private RaftState raftState;

    @PostMapping("/append")
    public ResponseEntity<?> append(@RequestBody Block block) {
        boolean success = raftState.appendEntry(block);
        return success ? ResponseEntity.ok("Appended") : ResponseEntity.status(409).body("Rejected");
    }

    @PostMapping("/vote")
    public ResponseEntity<?> vote(@RequestParam int term, @RequestParam String candidateId) {
        boolean granted = raftState.requestVote(term, candidateId);
        return ResponseEntity.ok(granted);
    }

    @GetMapping("/committed")
    public List<Block> getCommitted() {
        return raftState.getCommittedBlocks();
    }
}
