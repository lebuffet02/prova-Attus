package api.usuarios.service.impl;

import api.usuarios.dto.UserDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.UserNotFoundException;
import api.usuarios.mapper.MapperUser;
import api.usuarios.repository.UserRepository;
import api.usuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    MapperUser mapper;

    @Override
    public List<UserDTO> getAllUsersServiceImpl() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserByIdServiceImpl(Long id) {
        try {
            Optional<UserEntity> userEntity = repository.findById(id);
            return userEntity.map(u -> mapper.entityToDTO(u));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(String.format("%s", "User id not found"));
        }
    }

    @Override
    public void deleteUserByIdServiceImpl(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new UserNotFoundException();
            }
            repository.deleteById(id);
        }  catch (UserNotFoundException e) {
            throw new UserNotFoundException(String.format("%s", "User id not found"));
        }
    }

    @Override
    public void deleteAllUsersServiceImpl() {
        try {
            repository.deleteAll();
        }  catch (UserNotFoundException e) {
            throw new UserNotFoundException(String.format("%s", "Users aren't present to be deleting"));
        }
    }


}
