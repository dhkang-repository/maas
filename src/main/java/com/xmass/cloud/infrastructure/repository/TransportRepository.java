package com.xmass.cloud.infrastructure.repository;

import com.xmass.cloud.domain.transport.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByType(String type);
}
