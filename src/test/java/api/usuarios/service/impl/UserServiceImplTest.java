package api.usuarios.service.impl;

import api.usuarios.dto.AddressDTO;
import api.usuarios.dto.UserDTO;
import api.usuarios.entity.AddressEntity;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.SomeException;
import api.usuarios.exception.UserCannotBeRegisteredException;
import api.usuarios.exception.UserNotFoundException;
import api.usuarios.exception.UsersCannotBeDeletedException;
import api.usuarios.mapper.MapperUser;
import api.usuarios.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl serviceImpl;
    @Mock
    MapperUser mapper;
    @Mock
    UserRepository repository;

    private static final Long ID = 1L;


    @DisplayName("Salva usuário no banco de dados com sucesso.")
    @Test
    void testasaveUserServiceImpl() {
        UserEntity user = userEntity();
        when(mapper.dtoToEntity(userDTO())).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        Assertions.assertNotNull(serviceImpl.saveUserServiceImpl(userDTO()));
    }

    @DisplayName("Retorna nulo ao não informar o nomeCompleto.")
    @Test
    void testasaveUserServiceImplRetornandoEmpty() {
        Assertions.assertEquals(Optional.empty(), serviceImpl.saveUserServiceImpl(userDTOComNomeCompletoVazio()));
    }

    @DisplayName("Lança exceção do tipo SomeException no saveUserServiceImpl.")
    @Test
    void lancaExcecaoDoTipoSomeExceptionNoSaveUserServiceImpl() {
        when(mapper.dtoToEntity(userDTO())).thenThrow(UserCannotBeRegisteredException.class);
        Assertions.assertThrows(UserCannotBeRegisteredException.class, () -> serviceImpl.saveUserServiceImpl(userDTO()));
    }

    @DisplayName("Lança exceção do tipo UserCannotBeRegisteredException no saveUserServiceImpl.")
    @Test
    void lancaExcecaoDoTipoUsersCannotBeDeletedExceptionNoSaveUserServiceImpl() {
        when(mapper.dtoToEntity(userDTO())).thenThrow(SomeException.class);
        Assertions.assertThrows(SomeException.class, () -> serviceImpl.saveUserServiceImpl(userDTO()));
    }

    @DisplayName("Atualiza usuário no banco de dados com sucesso.")
    @Test
    void testaUpdateUserServiceImpl() {
        UserEntity user = userEntity();
        when(repository.findById(ID)).thenReturn(Optional.of(user));
        when(mapper.dtoToEntity(userDTO())).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        Assertions.assertNotNull(serviceImpl.updateUserServiceImpl(ID, userDTO()));
    }

    @DisplayName("Retorna nulo ao não encontrar o usuãrio.")
    @Test
    void testaUpdateUserServiceImplRetornandoEmpty() {
        Assertions.assertEquals(Optional.empty(), serviceImpl.updateUserServiceImpl(ID,null));
    }

    @DisplayName("Lança exceção do tipo UserCannotBeRegisteredException no updateUserServiceImpl.")
    @Test
    void lancaExcecaoDoTipoUsersCannotBeDeletedExceptionNoUpdateUserServiceImpl() {
        when(repository.findById(ID)).thenThrow(UserCannotBeRegisteredException.class);
        Assertions.assertThrows(UserCannotBeRegisteredException.class, () -> serviceImpl.updateUserServiceImpl(ID, userDTO()));
    }

    @DisplayName("Lança exceção do tipo SomeException no updateUserServiceImpl.")
    @Test
    void lancaExcecaoDoTipoSomeExceptionNoUpdateUserServiceImpl() {
        when(repository.findById(ID)).thenThrow(SomeException.class);
        Assertions.assertThrows(SomeException.class, () -> serviceImpl.updateUserServiceImpl(ID,userDTO()));
    }

    @DisplayName("Realiza a deleção de todos os usuários cadastrados.")
    @Test
    void testaDeleteAllUsersServiceImpl() {
        serviceImpl.deleteAllUsersServiceImpl();
        verify(repository, times(1)).deleteAll();
    }

    @DisplayName("Lança exceção do tipo UsersCannotBeDeletedException no deleteUserByIdServiceImpl.")
    @Test
    void lancaExcecaoDoTipoUsersCannotBeDeletedException() {
        doThrow(new RuntimeException()).when(repository).deleteAll();
        assertThrows(UsersCannotBeDeletedException.class, () -> serviceImpl.deleteAllUsersServiceImpl());
    }

    @DisplayName("Realiza a deleção de um usuário através do seu id no deleteUserByIdServiceImpl.")
    @Test
    void testaDeleteUserByIdServiceImpl() {
        when(repository.existsById(ID)).thenReturn(true);
        serviceImpl.deleteUserByIdServiceImpl(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @DisplayName("Não realiza a deleção de um usuário através do seu id no deleteUserByIdServiceImpl pois não passou pelo IF.")
    @Test
    void testaDeleteUserByIdServiceImplLancandoException() {
        when(repository.existsById(ID)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> serviceImpl.deleteUserByIdServiceImpl(ID));
    }

    @DisplayName("Lança exceção do tipo UserNotFoundException no deleteUserByIdServiceImpl.")
    @Test
    void lancaExcecaoDoTipoUserNotFoundException() {
        when(repository.existsById(ID)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> serviceImpl.deleteUserByIdServiceImpl(ID));
    }

    @DisplayName("Lança exceção do tipo ServerException no deleteUserByIdServiceImpl.")
    @Test
    void lancaExcecaoDoTipoServerException() {
        when(repository.existsById(ID)).thenThrow(SomeException.class);
        assertThrows(SomeException.class, () -> serviceImpl.deleteUserByIdServiceImpl(ID));
    }

    @DisplayName("Lança exceção do tipo UserNotFoundException no getUserByIdServiceImpl.")
    @Test
    void testaGetUserByIdServiceImpl() {
        UserEntity userEntity = userEntity();
        when(repository.findById(ID)).thenReturn(Optional.of(userEntity));
        when(mapper.entityToDTO(userEntity)).thenReturn(userDTO());
        assertNotNull(serviceImpl.getUserByIdServiceImpl(ID));
    }

    @DisplayName("Lança exceção do tipo UserNotFoundException no getUserByIdServiceImpl.")
    @Test
    void lancaExcecaoDoTipoUserNotFoundExceptionNoGetUserByIdServiceImpl() {
        when(repository.findById(ID)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> assertNotNull(serviceImpl.getUserByIdServiceImpl(ID)));
    }

    @DisplayName("Pega todos os usuário cadastrados no getAllUsersServiceImpl.")
    @Test
    void testaGetAllUsersServiceImpl() {
        UserDTO dto = userDTO();
        when(repository.findAll()).thenReturn(Collections.singletonList(new UserEntity()));
        when(mapper.entityToDTO(new UserEntity())).thenReturn(dto);
        assertNotNull(serviceImpl.getAllUsersServiceImpl());
    }

    private UserEntity userEntity() {
        return new UserEntity(ID, "teste", "teste@gmail.com","00000000000", "12/10/2008", new AddressEntity());
    }

    private UserDTO userDTO() {
        return new UserDTO("lucas", "teste@gmail.com", "00000000000",
                "12/02/2002", new AddressDTO("rs", "porto alegre", "alfredo",
                "00000000", "1"));
    }

    private UserDTO userDTOComNomeCompletoVazio() {
        return new UserDTO("", "teste@gmail.com", "00000000000",
                "12/02/2002", new AddressDTO("rs", "porto alegre", "alfredo",
                "00000000", "1"));
    }
}