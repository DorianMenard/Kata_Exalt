package com.example.exalt.services;

import com.example.exalt.entities.Pass;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class PassRequest {
    private final PriorityBlockingQueue<Pass> queue = new PriorityBlockingQueue<>(100, new VIPComparator());

    public void submitRequest(Pass pass) {
        queue.add(pass);
    }

    public Pass consumeRequest() throws InterruptedException {
        return queue.take();
    }
}

