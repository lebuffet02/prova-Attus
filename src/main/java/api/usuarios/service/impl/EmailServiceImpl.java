package api.usuarios.service.impl;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.EmailException;
import api.usuarios.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class EmailServiceImpl {

    @Autowired
    UserRepository repository;
    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendEmail(EmailRequestDTO emailDTO) {
        try {
            Optional<UserEntity> usuarioEntity = repository.findByEmail(removeSpace(emailDTO.to()));
            if(usuarioEntity.isEmpty()) {
                throw new EmailException("Email not found for: ".concat(emailDTO.to()));
            }
            log.info("toEmail: {} subJect: {} emailMessage: {}", emailDTO.to(), emailDTO.subject(), emailDTO.message());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(getEmailSentBy(emailDTO));
            mailMessage.setTo(emailDTO.to());
            mailMessage.setSubject(emailDTO.subject());
            mailMessage.setText(emailDTO.message());
            mailSender.send(mailMessage);
        }
        catch (Exception e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw new EmailException(e.getMessage());
        }
    }

    private String getEmailSentBy(EmailRequestDTO emailFrom) {
        if(!emailFrom.isEmailValid(emailFrom.from())) {
            throw new EmailException();
        }
        return emailFrom.from();
    }

    private static String removeSpace(String email) {
        return !email.isEmpty() ? email.replaceAll("\\D", "") : email;
    }
}