package com.example.exalt.controllers;

import com.example.exalt.entities.Pass;
import com.example.exalt.repositories.PassRepository;
import com.example.exalt.services.PassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PassController {
    private final PassService passService;
    private final PassRepository passRepository;

    public PassController(PassService passService, PassRepository passRepository) {
        this.passService = passService;
        this.passRepository = passRepository;
    }

    @PostMapping("/passes")
    public ResponseEntity<Void> requestPasses(@RequestBody List<Pass> passRequests) {
        passService.generatePasses(passRequests);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getPasses(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        List<Pass> passes = passRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dob);
        return ResponseEntity.ok(passes);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Server is running");
    }
}
