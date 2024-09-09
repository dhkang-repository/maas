package com.xmass.cloud.domain.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmass.cloud.infrastructure.repository.KakaoRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final KakaoRouteRepository kakaoRouteRepository;

    public String getRecommendedRoute(String origin, String destination) {
        // Google Maps API로부터 경로 데이터를 가져옴
        ResponseEntity<String> response = kakaoRouteRepository.get(origin, destination, "");

        // 결과를 가공하여 반환
        return processDirectionsResponse(response.getBody());
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
