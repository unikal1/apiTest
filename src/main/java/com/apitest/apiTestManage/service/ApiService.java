package com.apitest.apiTestManage.service;

import com.apitest.apiTestManage.entity.ApiInfo;
import com.apitest.apiTestManage.api.RequestServer;
import com.apitest.apiTestManage.dao.ApiInfoRepository;
import com.apitest.apiTestManage.dto.ApiDetailsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <br>package name   : com.apitest.apiTestManage.service
 * <br>file name      : ApiService
 * <br>date           : 2024-08-24
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-08-24        jack8              init create
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class ApiService {
    private final ApiInfoRepository apiInfoRepository;
    private final ObjectMapper objectMapper;
    private final RequestServer requestServer;

    public List<ApiDetailsDto> getAllApiInfo() {
        List<ApiInfo> apis = apiInfoRepository.findAll();
        return apis.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public Map.Entry<Integer, String> sendApiRequest(String title) {
        ApiInfo apiInfo = apiInfoRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("API not found"));

        // 실제 요청을 보내는 로직
        return requestServer.sendRequest(apiInfo);
    }

    private ApiDetailsDto convertToDto(ApiInfo info) {
        return ApiDetailsDto.builder()
                .title(info.getTitle())
                .url(info.getUrl())
                .method(info.getMethod())
                .isAuth(info.isAuth())
                .body(info.getBody())
                .expectedResponse(info.getExpectedResponse())
                .note(info.getNote())
                .build();
    }


}
