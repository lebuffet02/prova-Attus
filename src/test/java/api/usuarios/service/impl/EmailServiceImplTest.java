package api.usuarios.service.impl;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.EmailException;
import api.usuarios.exception.InvalidEmailException;
import api.usuarios.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    UserRepository repository;
    @Mock
    JavaMailSender mailSender;
    @InjectMocks
    EmailServiceImpl serviceImpl;

    @DisplayName("Realiza o envio de email corretamente.")
    @Test
    void deveRealizarOEnvioDeEmailComSucesso() {
        UserEntity userEntity = entity();
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        serviceImpl.sendEmail(getEmail());
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @DisplayName("Lança exception do tipo EmailException.")
    @Test
    void lancaExceptionDoTipoEmailException() {
        when(repository.findByEmail(anyString())).thenThrow(EmailException.class);
        Assertions.assertThrows(Exception.class, () -> serviceImpl.sendEmail(getEmail()));
    }

    @DisplayName("Lança exception do tipo InvalidEmailException.")
    @Test
    void lancaExceptionDoTipoInvalidEmailException() {
        when(repository.findByEmail(anyString())).thenThrow(InvalidEmailException.class);
        Assertions.assertThrows(Exception.class, () -> serviceImpl.sendEmail(getEmail()));
    }

    @DisplayName("Lança exception do tipo InvalidEmailException com email vazio.")
    @Test
    void lancaExceptionDoTipoInvalidEmailEdxceptioComEmailNaoValido() {
        Assertions.assertThrows(EmailException.class, () -> serviceImpl.sendEmail(new EmailRequestDTO("", "","", "")));
    }

    @DisplayName("Lança exception do tipo InvalidEmailException com email vazio.")
    @Test
    void lancaExceptionDoTipoInvalidEmailEdxceptioComEmailNaoValidod() {
        UserEntity userEntity = entity();
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        Assertions.assertThrows(InvalidEmailException.class, () -> serviceImpl.sendEmail(new EmailRequestDTO("le bu@gmail.com",
                "tes@gmail.com","a", "a")));
    }

    private EmailRequestDTO getEmail() {
        return new EmailRequestDTO("teste@gmail.com", "teste1@gmail.com",
                "teste", "blabla");
    }

    private UserEntity entity() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        return user;
    }
}