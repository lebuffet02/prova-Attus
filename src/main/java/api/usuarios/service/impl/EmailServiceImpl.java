package api.usuarios.service.impl;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.EmailException;
import api.usuarios.exception.InvalidEmailException;
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
            log.info("toEmail: {} subJect: {} emailMessage: {}", emailDTO.to(), emailDTO.subject(), emailDTO.body());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(getEmailSentBy(emailDTO));
            mailMessage.setTo(emailDTO.to().toLowerCase());
            mailMessage.setSubject(emailDTO.subject());
            mailMessage.setText(emailDTO.body());
            mailSender.send(mailMessage);
        }
        catch (EmailException e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw new EmailException(e.getMessage());
        }
        catch (InvalidEmailException e) {
            throw new InvalidEmailException(e.getMessage());
        }
    }

    private String getEmailSentBy(EmailRequestDTO emailSentBy) {
        if(!emailSentBy.isEmailValid(emailSentBy.sentBy())) {
            throw new InvalidEmailException();
        }
        return emailSentBy.sentBy();
    }

    private static String removeSpace(String email) {
        if (!email.isEmpty()) {
            return email.replaceAll("\\D", "");
        }
        return email;
    }
}