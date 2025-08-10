package com.javatar.raftnodeservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private int index;
    private String previousHash;
    private String data;
    private long timestamp;
    private String hash;

    public String calculateHash() {
        return DigestUtils.sha256Hex(index + previousHash + data + timestamp);
    }
}

