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

    public UserEntity dtoToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .nomeCompleto(userDTO.nomeCompleto())
                .email(userDTO.email())
                .cpf(userDTO.cpf())
                .dataNascimento(userDTO.dataNascimento())
                .enderecos(addressDTOToEntity(userDTO.addressDTO()))
                .build();
    }

    private AddressDTO addressEntityToDTO(AddressEntity addressEntity) {
        return AddressDTO.builder()
                .estado(addressEntity.getEstado().toUpperCase())
                .cidade(addressEntity.getCidade())
                .logradouro(addressEntity.getLogradouro())
                .cep(addressEntity.getCep())
                .numero(addressEntity.getNumero())
                .build();
    }

    private AddressEntity addressDTOToEntity(AddressDTO addressDTO) {
        return AddressEntity.builder()
                .estado(addressDTO.estado().toUpperCase())
                .cidade(addressDTO.cidade())
                .logradouro(addressDTO.logradouro())
                .cep(addressDTO.cep())
                .numero(addressDTO.numero())
                .build();
    }
}