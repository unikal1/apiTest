package com.apitest.apiTestManage.api;

import com.apitest.apiTestManage.entity.ApiInfo;
import com.apitest.apiTestManage.entity.Headers;
import com.apitest.utils.LogSanitizer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RequestServer {

    private final WebClient webClient;
    private final LogSanitizer logSanitizer;

    private final ConcurrentHashMap<String, Disposable> sessionLogStreams = new ConcurrentHashMap<>();


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

    public Flux<String> streamLogs(String containerName, String sessionId) {
        System.out.println("session start with id : " + sessionId);
        String url = "http://192.168.219.135:2375/containers/" + containerName + "/logs?stdout=true&stderr=true&follow=true&tail=0";

        Flux<String> logStream = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(byte[].class)
                .map(this::removeDockerLogHeader)
                .doOnError(error -> {
                    System.err.println("Error occurred while streaming logs: " + error.getMessage());
                });

        // 로그 스트림을 시작하고 Disposable 객체를 저장
        Disposable disposable = logStream.subscribe(log -> {
            // 로그를 WebSocket으로 클라이언트에 전송하거나 다른 작업 수행
            System.out.println("Streaming log: " + log);
        });
        sessionLogStreams.put(sessionId, disposable);
        System.out.println(sessionLogStreams.keys());

        return logStream;
    }

    public void stopLogStreaming(String sessionId) {
        // 세션 ID에 해당하는 로그 스트리밍 작업을 중단
        Disposable disposable = sessionLogStreams.remove(sessionId);
        if (disposable != null) {
            disposable.dispose();
            System.out.println("Stopped Docker log stream for session ID: " + sessionId);
        }
    }

    private String removeDockerLogHeader(byte[] logBytes) {
        // Docker 로깅 헤더는 8바이트입니다.
        if (logBytes.length > 8) {
            byte[] cleanLog = Arrays.copyOfRange(logBytes, 8, logBytes.length);
            return new String(cleanLog, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }


}
