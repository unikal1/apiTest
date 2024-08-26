package com.apitest.apiTestManage.api;

import com.apitest.apiTestManage.dto.ApiDetailsDto;
import com.apitest.apiTestManage.dto.ApiRequestDto;
import com.apitest.apiTestManage.dto.SuccessResponseDto;
import com.apitest.apiTestManage.service.ApiService;
import lombok.RequiredArgsConstructor;
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



    @GetMapping("/api")
    public String getApiPage(@RequestParam("type") String type, Model model) {
        List<ApiDetailsDto> apiList = apiService.getAllApiInfo(type);
        model.addAttribute("apiList", apiList);
        return "api_test/api_page";
    }

    @GetMapping("/request")
    @ResponseBody
    public Map<String, Object> sendRequest(@RequestParam("id") Long id) {
        Map.Entry<Integer,String> result  = apiService.sendApiRequest(id);
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
        apiService.saveApi(apiRequestDto);

        return ResponseEntity.ok(new SuccessResponseDto("success"));
    }

    @GetMapping("/api/search")
    @ResponseBody
    public List<ApiDetailsDto> searchApi(@RequestParam("title") String title) {
        return apiService.searchApi(title);
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<?> deleteApi(@RequestParam("id")Long id) {
        try {
            apiService.deleteApi(id);
            return ResponseEntity.ok(new SuccessResponseDto("success"));
        } catch(Exception e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }


    @GetMapping("/api/delete")
    public String deleteApi() {
        return "api_test/delete_api";
    }

}
