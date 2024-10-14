package com.xmass.cloud.domain.account.repository;

import com.xmass.cloud.domain.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final WebClient webClient;

    public Mono<List<Account>> get() {
        // WebClient를 이용한 비동기 요청
        Mono<List<Account>> response = webClient.get()
                .uri("/v1/accounts")
                .retrieve() // 응답 처리
                .bodyToMono(new ParameterizedTypeReference<List<Account>>() {}); // 응답 본문을 String으로 변환

        // 비동기 응답 처리
        return response;
    }

}
