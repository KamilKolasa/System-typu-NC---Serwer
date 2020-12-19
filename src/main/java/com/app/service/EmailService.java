package com.app.service;


import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.User;
import com.app.model.security.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(VerificationToken verificationToken, HttpServletRequest request, String urlFragment) {

        if (verificationToken == null) {
            throw new MyException(ExceptionCode.SERVICE, "verification token is null");
        }

        if (verificationToken.getUser() == null) {
            throw new MyException(ExceptionCode.SERVICE, "user is null");
        }

        if (verificationToken.getToken() == null) {
            throw new MyException(ExceptionCode.SERVICE, "token is null");
        }

        User user = verificationToken.getUser();
        String token = verificationToken.getToken();

        String recipientAddress = user.getEmail();
        String subject = "Registration confirmation";

        String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
        String message = "Activation link: " + url + "security/" + urlFragment + "?token=" + token;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);
    }
}
