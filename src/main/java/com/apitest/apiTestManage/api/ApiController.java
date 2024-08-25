package com.apitest.apiTestManage.api;

import com.apitest.apiTestManage.dto.ApiDetailsDto;
import com.apitest.apiTestManage.dto.ApiRequestDto;
import com.apitest.apiTestManage.dto.SuccessResponseDto;
import com.apitest.apiTestManage.service.ApiService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>package name   : com.apitest.apiTestManage
 * <br>file name      : ApiController
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
@Controller
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/member_api")
    public String memberApi(Model model) {
        List<ApiDetailsDto> apiList = apiService.getAllApiInfo();
        System.out.println(apiList);
        model.addAttribute("apiList", apiList);
        return "api_test/member_api"; // "api_test" 디렉토리 아래에 있는 "member_api.html"을 가리킵니다.
    }

    @GetMapping("/request")
    @ResponseBody
    public Map<String, Object> sendRequest(@RequestParam("title") String title) {
        Map.Entry<Integer,String> result  = apiService.sendApiRequest(title);
        String code = result.getKey().toString();
        String response = result.getValue();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", code);
        responseBody.put("response", response);

        return responseBody;
    }

    @GetMapping("/api/add")
    public String addApi() {
        return "api_test/add_api";
    }

    @PostMapping("/api/save")
    @ResponseBody
    public ResponseEntity<SuccessResponseDto> saveApi(@RequestBody ApiRequestDto apiRequestDto) {

        return ResponseEntity.ok(new SuccessResponseDto("success"));
    }

}
