package com.xmass.cloud.application;

import com.xmass.cloud.domain.transport.model.Transport;
import com.xmass.cloud.infrastructure.exception.ResourceNotFound;
import com.xmass.cloud.domain.transport.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApplication {
    private final TransportRepository transportRepository;

    public void saveTransport(Transport transport) {
        transportRepository.save(transport);
    }

    public Transport getTransport(Long id) {
        return transportRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Transport not found"));
    }
}
