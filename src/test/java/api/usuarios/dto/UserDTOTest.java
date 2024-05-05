package api.usuarios.dto;

import api.usuarios.exception.UserCannotBeRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDTOTest {

    @InjectMocks
    UserDTO userDTO;

    @DisplayName("Email deve ser válido com sucesso.")
    @Test
    void testaIsEmailValid() {
        Assertions.assertNotNull(userDTO.isEmailValid("teste@gmail.com"));
    }

    @DisplayName("Lança exceção do tipo UserCannotBeRegisteredException pois o email não é válido.")
    @Test
    void lancaExcecaoNoIsEmailValid() {
        Assertions.assertThrows(UserCannotBeRegisteredException.class, () -> userDTO.isEmailValid("tf tf@gmail.com"));
    }
}