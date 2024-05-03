package api.usuarios.controller;

import api.usuarios.dto.AddressDTO;
import api.usuarios.dto.UserDTO;
import api.usuarios.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserServiceImpl serviceImpl;
    @InjectMocks
    UserController controller;

    private static final Long ID = 1L;

    @DisplayName("Testa chamada da service saveUserServiceImpl")
    @Test
    void testaSaveUser() {
        controller.saveUser(any());
        verify(serviceImpl, times(1)).saveUserServiceImpl(any());
    }

    @DisplayName("Testa chamada da service updateUserServiceImpl")
    @Test
    void testaUpdateUser() {
        UserDTO userDTO = userDTO();
        controller.updateUser(ID, userDTO);
        verify(serviceImpl, times(1)).updateUserServiceImpl(ID, userDTO);
    }

    @DisplayName("Testa chamada da service getAllUsersServiceImpl")
    @Test
    void testaGetAllUsers() {
        controller.getAllUsers();
        verify(serviceImpl, times(1)).getAllUsersServiceImpl();
    }

    @DisplayName("Testa chamada da service getUserByIdServiceImpl")
    @Test
    void testaGetUserById() {
        controller.getUserById(ID);
        verify(serviceImpl, times(1)).getUserByIdServiceImpl(ID);
    }

    @DisplayName("Testa chamada da service deleteAllUsersServiceImpl")
    @Test
    void testaDeleteAllUsers() {
        controller.deleteAllUsers();
        verify(serviceImpl, times(1)).deleteAllUsersServiceImpl();
    }

    @DisplayName("Testa chamada da service deleteUserByIdServiceImpl")
    @Test
    void testaDeleteUserById() {
        controller.deleteUserById(ID);
        verify(serviceImpl, times(1)).deleteUserByIdServiceImpl(ID);
    }

    private UserDTO userDTO() {
        return new UserDTO("lucas", "teste@gmail.com", "00000000000",
                LocalDate.now(), new AddressDTO("rs", "porto alegre", "alfredo",
                "00000000", "1"));
    }
}