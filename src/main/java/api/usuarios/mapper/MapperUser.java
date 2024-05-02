package api.usuarios.mapper;

import api.usuarios.dto.AddressDTO;
import api.usuarios.dto.UserDTO;
import api.usuarios.entity.AddressEntity;
import api.usuarios.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

    public UserDTO entityToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .nomeCompleto(userEntity.getNomeCompleto())
                .email(userEntity.getEmail())
                .cpf(userEntity.getCpf())
                .dataNascimento(userEntity.getDataNascimento())
                .addressDTO(addressEntityToDTO(userEntity.getEnderecos()))
                .build();
    }

    private AddressDTO addressEntityToDTO(AddressEntity addressEntity) {
        return AddressDTO.builder()
                .estado(addressEntity.getEstado())
                .cidade(addressEntity.getCidade())
                .logradouro(addressEntity.getLogradouro())
                .cep(addressEntity.getCep())
                .numero(addressEntity.getNumero())
                .build();
    }
}