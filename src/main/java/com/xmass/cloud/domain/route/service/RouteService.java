package com.xmass.cloud.domain.route.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmass.cloud.domain.route.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public String getRecommendedRoute(String origin, String destination) {

        return null;
    }

    private String processDirectionsResponse(String response) {
        // JSON 응답을 객체로 변환하고 경로 정보를 추출
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode routes = rootNode.get("routes").get(0);
            JsonNode legs = routes.get("legs").get(0);
            String distance = legs.get("distance").get("text").asText();
            String duration = legs.get("duration").get("text").asText();

            return "최적 경로: 거리 - " + distance + ", 예상 시간 - " + duration;
        } catch (Exception e) {
            e.printStackTrace();
            return "경로 처리 중 오류 발생";
        }
    }
}
