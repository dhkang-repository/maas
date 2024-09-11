package com.xmass.cloud.web.controller;

import com.xmass.cloud.application.TransportApplication;
import com.xmass.cloud.domain.transport.model.Transport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transports")
@RequiredArgsConstructor
public class TransportController {
    private final TransportApplication transportService;

    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransport(@PathVariable Long id) {
        Transport transport = transportService.getTransport(id);
        return ResponseEntity.ok(transport);
    }

    @PostMapping
    public ResponseEntity<Void> saveTransport(@RequestBody Transport transport) {
        transportService.saveTransport(transport);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
