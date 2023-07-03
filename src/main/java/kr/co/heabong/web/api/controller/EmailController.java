package kr.co.heabong.web.api.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.Email;
import kr.co.heabong.web.repository.EmailPostDto;
import kr.co.heabong.web.repository.EmailResponseDto;
import kr.co.heabong.web.service.EmailService;
import lombok.RequiredArgsConstructor;


@RequestMapping("/send-mail")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


	// 임시 비밀번호 발급 
    @PostMapping("/password")
    public ResponseEntity sendPasswordMail(@RequestBody EmailPostDto emailPostDto) throws UnsupportedEncodingException {
        Email email = Email.builder()
                .to("rlaqjatn3663@naver.com")
                .subject("[SAVIEW] 임시 비밀번호 발급")
                .build();

        emailService.sendMail(email, "password");

        return ResponseEntity.ok().build();
    }

	// 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("/email")
    public ResponseEntity sendJoinMail(@RequestBody EmailPostDto emailPostDto) throws UnsupportedEncodingException {
        Email emailMessage = Email.builder()
                .to(emailPostDto.getEmail())
                .subject("[SAVIEW] 이메일 인증을 위한 인증 코드 발송")
                .build();

        String code = emailService.sendMail(emailMessage, "email");

        EmailResponseDto emailResponseDto = new EmailResponseDto();
        emailResponseDto.setCode(code);

        return ResponseEntity.ok(emailResponseDto);
    }
}