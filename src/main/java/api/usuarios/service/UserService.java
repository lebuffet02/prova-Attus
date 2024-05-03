package api.usuarios.service;

import api.usuarios.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> saveUserServiceImpl(UserDTO userDTO);

    Optional<UserDTO> updateUserServiceImpl(Long id, UserDTO userDTO);

    List<UserDTO> getAllUsersServiceImpl();

    Optional<UserDTO> getUserByIdServiceImpl(Long id);

    void deleteUserByIdServiceImpl(Long id);

    void deleteAllUsersServiceImpl();
}
