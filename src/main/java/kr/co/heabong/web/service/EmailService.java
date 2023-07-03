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
    private final JavaMailSender javaMailSender;
	@Autowired
    private final SpringTemplateEngine templateEngine;
	@Autowired
    private final UserService userService;

    public String sendMail(Email email, String type) throws UnsupportedEncodingException {
        String authNum = createCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setFrom(new InternetAddress("haevong.com","해봉","UTF-8"));
            mimeMessageHelper.setTo(email.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(email.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Success");

            return authNum;

        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }

    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    // thymeleaf를 통한 html 적용
    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
    
 public void sendMail() {
        
        // ���� ����� ���� ArrayList ����
        ArrayList<String> toUserList = new ArrayList<>();
        
        // ���� ��� �߰�
        toUserList.add("rlaqjatn3663@naver.com");
        
        // ���� ��� ����
        int toUserSize = toUserList.size();
        
        // SimpleMailMessage (�ܼ� �ؽ�Ʈ ���� ���� �޽��� ������ �� �̿�)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        
        // ������ ����
        simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
       // simpleMessage.setFrom("해봉");
        // ���� ����
        simpleMessage.setSubject("Subject Sample");
        
        // ���� ����
        simpleMessage.setText("Text Sample");
        
        // ���� �߼�
        javaMailSender.send(simpleMessage);
    }
}
