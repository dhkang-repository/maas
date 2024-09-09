package com.xmass.cloud.application;

import com.xmass.cloud.domain.transport.Transport;
import com.xmass.cloud.infrastructure.exception.ResourceNotFound;
import com.xmass.cloud.infrastructure.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportApplication {
    private final TransportRepository transportRepository;

    public void saveTransport(Transport transport) {
        transportRepository.save(transport);
    }

    public Transport getTransport(Long id) {
        return transportRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Transport not found"));
    }
}
