package api.usuarios.service.impl;

import api.usuarios.dto.UserDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.*;
import api.usuarios.mapper.MapperUser;
import api.usuarios.repository.UserRepository;
import api.usuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    MapperUser mapper;

    public Optional<UserDTO> saveUserServiceImpl(UserDTO userDTO) {
        try {
            if(!userDTO.nomeCompleto().isEmpty() && userDTO.isEmailValid(userDTO.email())) {
                UserEntity userEntity = mapper.dtoToEntity(userDTO);
                validateEmailExists(userEntity.getEmail());
                repository.save(userEntity);
                return Optional.ofNullable(mapper.entityToDTO(userEntity));
            }
        } catch (RuntimeException ex) {
            throw (ex instanceof UserCannotBeRegisteredException) ?
                    new UserCannotBeRegisteredException(String.format("%s", ex.getMessage())) :
                    new SomeException(String.format("%s", ex.getMessage()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updateUserServiceImpl(Long id, UserDTO userDTO) {
        try {
            Optional<UserEntity> idUser = repository.findById(id);
            if(idUser.isPresent() && userDTO.isEmailValid(userDTO.email())) {
                UserEntity userEntity = mapper.dtoToEntity(userDTO);
                repository.save(userEntity);
                return Optional.ofNullable(mapper.entityToDTO(userEntity));
            }
        }   catch (RuntimeException ex) {
            throw (ex instanceof UserCannotBeRegisteredException) ?
                    new UserCannotBeRegisteredException(String.format("%s", "Problems to update user.")) :
                    new SomeException(String.format("%s", ex.getMessage()));
        }
        return Optional.empty();
    }

    @Override
    public List<UserDTO> getAllUsersServiceImpl() {
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<UserDTO> getUserByIdServiceImpl(Long id) {
        try {
            Optional<UserEntity> userEntity = repository.findById(id);
            return Optional.of(userEntity
                    .map(u -> mapper.entityToDTO(u))
                    .orElseThrow(() -> new UserNotFoundException("User not found.")));
        } catch (RuntimeException ex) {
            throw new UserNotFoundException(String.format("%s", ex.getMessage()));
        }
    }

    @Override
    public void deleteAllUsersServiceImpl() {
        try {
            repository.deleteAll();
        } catch (RuntimeException ex) {
            throw new UsersCannotBeDeletedException(String.format("%s", "Users wasn't found to be deleted."));
        }
    }

    @Override
    @Transactional
    public void deleteUserByIdServiceImpl(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new UserNotFoundException();
            }
            repository.deleteById(id);
        } catch (RuntimeException ex) {
            throw (ex instanceof UserNotFoundException) ?
                    new UserNotFoundException(String.format("%s", "User id not found to be deleted.")) :
                    new SomeException(String.format("%s", "other error occurred"));
        }
    }

    private void validateEmailExists(String email) {
        if(repository.findByEmail(email).isPresent()) {
            throw new UserCannotBeRegisteredException("Email already exists.");
        }
    }
}