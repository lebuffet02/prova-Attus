package api.usuarios.service.impl;

import api.usuarios.dto.UserDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.*;
import api.usuarios.mapper.MapperUser;
import api.usuarios.repository.UserRepository;
import api.usuarios.service.UserService;
import api.usuarios.utils.MaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

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
    public Optional<UserDTO> saveUserServiceImpl(UserDTO userDTO) {
        try {
            if(userDTO.isEmailValid(userDTO.email()) && MaskUtils.maskCpf(userDTO.cpf()) != null) {
                UserEntity userEntity = mapper.dtoToEntity(userDTO);
                validateEmailExists(userEntity.getEmail());
                repository.save(userEntity);
                return Optional.ofNullable(mapper.entityToDTO(userEntity));
            }
        } catch (HttpClientErrorException ex) {
            exceptionToSaveAndUpdate(ex,"Problems to save user");
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
        }  catch (HttpClientErrorException ex) {
            exceptionToSaveAndUpdate(ex,"Problems to update user");
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
            return userEntity.map(u -> mapper.entityToDTO(u));
        } catch (HttpClientErrorException ex) {
            exceptionTypes(ex, "User id not found");
        }
        return Optional.empty();
    }

    @Override
    public void deleteAllUsersServiceImpl() {
        try {
            repository.deleteAll();
        } catch (HttpClientErrorException ex) {
            exceptionTypes(ex, "Users aren't present to be deleting");
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
        } catch (HttpClientErrorException ex) {
            exceptionTypes(ex, "User id not found");
        }
    }

    private void validateEmailExists(String email) {
        if(repository.findByEmail(email).isPresent()) {
            return;
        }
        throw new UserCanotBeRegisteredException();
    }

    private void exceptionTypes(HttpClientErrorException ex, String message) {
        switch (ex.getStatusCode().value()) {
            case 401 -> throw new UnauthorizedExcepion(String.format("%s", "Endpoint is blocked"));
            case 403 -> throw new ForbiddenException(String.format("%s", "Generate the AccessToken"));
            case 500 -> throw new ServerException(String.format("%s", "Server Error"));
            default -> throw new UserNotFoundException(String.format("%s", message));
        }
    }

    private void exceptionToSaveAndUpdate(HttpClientErrorException ex, String message) {
        switch (ex.getStatusCode().value()) {
            case 401 -> throw new UnauthorizedExcepion(String.format("%s", "Endpoint is blocked"));
            case 403 -> throw new ForbiddenException(String.format("%s", "Generate the AccessToken"));
            case 500 -> throw new ServerException(String.format("%s", "Server Error"));
            default -> throw new UserCanotBeRegisteredException(String.format("%s", message));
        }
    }
}