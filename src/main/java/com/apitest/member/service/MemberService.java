package com.apitest.member.service;

import com.apitest.member.Member;
import com.apitest.member.dao.MemberRepository;
import com.apitest.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <br>package name   : com.apitest.member.service
 * <br>file name      : MemberService
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
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public void signUp(SignUpRequestDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .email(dto.getEmail())
                .name(dto.getName())
                .build();

        memberRepository.save(member);
    }
}
