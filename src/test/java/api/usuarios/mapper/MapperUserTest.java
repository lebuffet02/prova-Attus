package api.usuarios.mapper;

import api.usuarios.dto.AddressDTO;
import api.usuarios.dto.UserDTO;
import api.usuarios.entity.AddressEntity;
import api.usuarios.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MapperUserTest {

    @InjectMocks
    MapperUser mapper;

    @DisplayName("Deve transformar o objeto UserEntity no UserDTO.")
    @Test
    void testaMapperBuilderToentityToDTO() {
        Assertions.assertNotNull(mapper.entityToDTO(userEntity()));
    }

    @DisplayName("Deve transformar o DTO UserDTO no UserEntity.")
    @Test
    void testaMapperBuilderToDTOToEntity() {
        Assertions.assertNotNull(mapper.dtoToEntity(userDTO()));
    }

    @DisplayName("Deve lançar exceção ao transformar o DTO UserDTO no UserEntity.")
    @Test
    void testaFormatacaoCpf() {
        Assertions.assertThrows(Exception.class, () -> mapper.dtoToEntity( new UserDTO("lucas", "teste@gmail.com",
                "000000000001","12/02/2002", null)));
    }

    @DisplayName("Deve lançar exceção ao transformar o DTO UserDTO no UserEntity.")
    @Test
    void testaFormatacaoCep() {
        Assertions.assertThrows(Exception.class, () -> mapper.dtoToEntity( new UserDTO("lucas", "teste@gmail.com",
                "00000000000","12/02/2002", new AddressDTO("rs", "poa", "rua"
        , "911401411", "1"))));
    }

    private UserDTO userDTO() {
        return new UserDTO("lucas", "teste@gmail.com", "00000000000",
                "12/02/2002", new AddressDTO("rs", "porto alegre", "alfredo",
                "00000000", "1"));
    }

    private UserEntity userEntity() {
        UserEntity user = new UserEntity();
        AddressEntity address = new AddressEntity();
        address.setEstado("rs");
        user.setNomeCompleto("Lucas Buffet");
        user.setEmail("teste@gmail.com");
        user.setCpf("11");
        user.setDataNascimento("");
        user.setEnderecos(address);
        return user;
    }
}