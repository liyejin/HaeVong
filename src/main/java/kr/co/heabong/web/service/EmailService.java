package kr.co.heabong.web.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import kr.co.heabong.web.entity.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

	@Autowired
	private final SpringTemplateEngine templateEngine;
	@Autowired
	private final UserService userService;

	@Autowired
	private JavaMailSender mailSender;
	
	/*
	 * public String sendMail(Email email, String type) throws
	 * UnsupportedEncodingException { String authNum = createCode();
	 * 
	 * MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	 * 
	 * try { MimeMessageHelper mimeMessageHelper = new
	 * MimeMessageHelper(mimeMessage, false, "UTF-8"); mimeMessageHelper.setFrom(new
	 * InternetAddress("haevong.com", "해봉", "UTF-8"));
	 * mimeMessageHelper.setTo(email.getTo()); // 메일 수신자
	 * mimeMessageHelper.setSubject(email.getSubject()); // 메일 제목
	 * mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML
	 * 여부 javaMailSender.send(mimeMessage);
	 * 
	 * log.info("Success");
	 * 
	 * return authNum;
	 * 
	 * } catch (MessagingException e) { log.info("fail"); throw new
	 * RuntimeException(e); } }
	 * 
	 * // 인증번호 및 임시 비밀번호 생성 메서드 public String createCode() { Random random = new
	 * Random(); StringBuffer key = new StringBuffer();
	 * 
	 * for (int i = 0; i < 8; i++) { int index = random.nextInt(4);
	 * 
	 * switch (index) { case 0: key.append((char) ((int) random.nextInt(26) + 97));
	 * break; case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
	 * default: key.append(random.nextInt(9)); } } return key.toString(); }
	 */

	public String sendVerificationEmail(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String code = generateVerificationCode();
        String[] codes = code.split("");
		helper.setFrom("vip@heavong.com", "해봉");
		helper.setTo(recipientEmail);
		helper.setSubject("인증코드 발송!");
		
		
        // HTML 템플릿 파일 읽어오기
        Context context = new Context();
        
        
        for(int i=0; i<codes.length; i++) {
        	context.setVariable("code"+(i+1), codes[i]);
        }
        
        String htmlContent = templateEngine.process("mail", context);

        helper.setText(htmlContent, true);

        mailSender.send(message);
        
        return code;
    }

	private String generateVerificationCode() {
		// 6자리 숫자 인증번호 생성
		Random random = new Random();
		int verificationNumber = random.nextInt(900000) + 100000;
		return String.valueOf(verificationNumber);
	}
}
