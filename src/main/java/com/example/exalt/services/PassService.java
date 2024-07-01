package com.example.exalt.services;

import com.example.exalt.entities.Pass;
import com.example.exalt.repositories.PassRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PassService {
    private final PassRepository passRepository;
    private final PassRequest passRequest;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public PassService(PassRepository passRepository, PassRequest passRequest) {
        this.passRepository = passRepository;
        this.passRequest = passRequest;
        startConsumers();
    }

    public void generatePasses(List<Pass> passRequests) {
        passRequests.forEach(pass -> {
            pass.setRequestTimestamp(LocalDateTime.now());
            passRequest.submitRequest(pass);
        });
    }

    private void startConsumers() {
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                while (true) {
                    try {
                        Pass pass = passRequest.consumeRequest();
                        processPass(pass);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    private void processPass(Pass pass) {
        pass.setGenerationTimestamp(LocalDateTime.now());
        passRepository.save(pass);
    }
}
