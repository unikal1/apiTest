package com.apitest.member.api;

import com.apitest.member.dto.SignUpRequestDto;
import com.apitest.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>package name   : com.apitest.member.api
 * <br>file name      : SignUpController
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
public class SignUpController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("signUpRequestDto", new SignUpRequestDto());
        return "signup";
    }

    @PostMapping ("/signup")
    public String signUp(@ModelAttribute("signUpRequestDto") SignUpRequestDto dto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        memberService.signUp(dto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=  authentication.getName();
        model.addAttribute("userName", username);
        return "home";
    }


}
