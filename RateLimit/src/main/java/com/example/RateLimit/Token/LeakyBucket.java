package com.example.RateLimit.Token;

import java.util.concurrent.TimeUnit;

public class LeakyBucket {

    private final long capacity; // The Maximum Size of the Bucket
    private final long refillRate;// How Many Tokens per Second
    private long currentLevel;// The Current number of Tokens in The Bucket
    private long lastLeakTimestamp;// Time when last leak occurred


    public LeakyBucket(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentLevel = capacity;
        this.lastLeakTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest()
    {
        leakTokens();
        if (currentLevel > 0)
        {
            currentLevel--;
            return true;// Accepts the Request if Bucket Have Tokens
        }
        return false;// Reject the request if Bucket is Empty
    }

    private void leakTokens() {

        long now = System.nanoTime();
        long elapsedTime = now - lastLeakTimestamp;
        long tokensToLeak = (elapsedTime * refillRate)/TimeUnit.SECONDS.toNanos(1);

        if (tokensToLeak >0)
        {
            currentLevel = Math.min(capacity, currentLevel + tokensToLeak);
            lastLeakTimestamp = now;
        }

    }

}
