package com.example.RateLimit.service;

import com.example.RateLimit.Token.LeakyBucket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, LeakyBucket> buckets = new ConcurrentHashMap<>();

    private final long BUCKET_CAPACITY = 10; // 10 Requests

    private final long REFILL_RATE = 2; // Tokens Per Seconds

    public boolean allowRequest(String clientId)
    {
        LeakyBucket bucket = buckets.computeIfAbsent(clientId , k -> new LeakyBucket(BUCKET_CAPACITY,REFILL_RATE));

        return bucket.allowRequest();
    }

}
