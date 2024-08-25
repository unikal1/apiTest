package com.apitest.apiTestManage.api;

import com.apitest.apiTestManage.entity.ApiInfo;
import com.apitest.apiTestManage.entity.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RequestServer {

    private final WebClient webClient;


    public Map.Entry<Integer, String> sendRequest(ApiInfo apiInfo) {
        String url = apiInfo.getUrl();
        String body = apiInfo.getBody();

        WebClient.RequestHeadersSpec<?> requestSpec;

        switch(apiInfo.getMethod().toUpperCase()) {
            case "GET":
                requestSpec = webClient.get().uri(url);
                break;

            case "POST":
                requestSpec = webClient.post()
                        .uri(url)
                        .bodyValue(body);
                break;

            case "PUT":
                requestSpec = webClient.put()
                        .uri(url)
                        .bodyValue(body);
                break;

            case "DELETE":
                requestSpec = webClient.delete().uri(url);
                break;

            default:
                throw new UnsupportedOperationException("Unsupported method");
        }

        for (Headers header : apiInfo.getHeadersList()) {
            requestSpec = requestSpec.header(header.getHeaderKey(), header.getValue());
        }
        Map.Entry<Integer, String> response = requestSpec.exchangeToMono(this::processResponse)
                .block();

        System.out.println("Status Code: " + response.getKey());
        System.out.println("Response Body: " + response.getValue());

        return response;
    }

    private  Mono<Map.Entry<Integer, String>> processResponse(ClientResponse response) {
        return response.bodyToMono(String.class)
                .map(body -> {
                    int statusCode = response.statusCode().value();
                    return new AbstractMap.SimpleEntry<>(statusCode, body);
                });
    }
}
