package api.usuarios.mapper;

import api.usuarios.entity.AddressEntity;
import api.usuarios.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class MapperUserTest {

    @InjectMocks
    MapperUser mapper;

    @DisplayName("Deve transformar o objeto UserEntity no UserDTO.")
    @Test
    void testaMapperBuilderToentityToDTO() {
        Assertions.assertNotNull(mapper.entityToDTO(userDTO()));
    }

    private UserEntity userDTO() {
        UserEntity user = new UserEntity();
        user.setNomeCompleto("Lucas Buffet");
        user.setEmail("teste@gmail.com");
        user.setCpf("11");
        user.setDataNascimento(LocalDateTime.now());
        user.setEnderecos(new AddressEntity());
        return user;
    }
}