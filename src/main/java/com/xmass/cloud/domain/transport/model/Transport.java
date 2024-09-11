package com.xmass.cloud.domain.transport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // 버스, 지하철, 전동킥보드 등
    private String name;
    private LocalDateTime arrivalTime;

    // 비즈니스 규칙 메서드
    public void updateArrivalTime(LocalDateTime newTime) {
        this.arrivalTime = newTime;
    }
}
