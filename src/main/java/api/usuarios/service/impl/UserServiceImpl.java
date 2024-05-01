package api.usuarios.service.impl;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.entity.UsuarioEntity;
import api.usuarios.exception.AuthException;
import api.usuarios.exception.EmailException;
import api.usuarios.repository.UsuarioRepository;
import api.usuarios.service.UserService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AmazonSimpleEmailService sesClient;
    @Autowired
    UsuarioRepository repository;


    @Override
    public void sendEmail(EmailRequestDTO emailDTO) {
        try {
            UsuarioEntity usuarioEntity = repository.findByEmail(removeSpace(emailDTO.to()))
                    .orElseThrow(() -> new EmailException("Email not found for : ".concat(emailDTO.to())));
            if(!ObjectUtils.isEmpty(usuarioEntity.getEmail())) {
                SendEmailRequest request = new SendEmailRequest()
                        .withSource(emailDTO.isEmailValid(emailDTO.sentBy()) ? emailDTO.sentBy() : null)
                        .withDestination(new Destination().withToAddresses(emailDTO.isEmailValid(emailDTO.to()) ? emailDTO.to() : null))
                        .withMessage(new Message().withSubject(new Content(emailDTO.subject()))
                                .withBody(new Body().withText(new Content(emailDTO.body()))));
                sesClient.sendEmail(request);
            }
        } catch (AmazonServiceException e) {
            throw new EmailException(String.format("%s", "Send Email failed..."));
        }
    }

    private static String removeSpace(String email) {
        if (!email.isEmpty()) {
            return email.replaceAll("\\D", "");
        }
        return email;
    }
}