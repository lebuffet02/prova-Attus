package api.usuarios.mapper;

import api.usuarios.dto.AddressDTO;
import api.usuarios.dto.UserDTO;
import api.usuarios.entity.AddressEntity;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.UserCannotBeRegisteredException;
import api.usuarios.utils.MaskUtils;
import org.apache.commons.lang3.StringUtils;
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
                .cpf(getCpf(userDTO.cpf()))
                .dataNascimento(userDTO.dataNascimento())
                .enderecos(addressDTOToEntity(userDTO.addressDTO()))
                .build();
    }

    private AddressDTO addressEntityToDTO(AddressEntity addressEntity) {
        return AddressDTO.builder()
                .estado(addressEntity.getEstado() != null ? addressEntity.getEstado().toUpperCase() : null)
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
                .cep(getCep(addressDTO.cep()))
                .numero(addressDTO.numero())
                .build();
    }

    private String getCpf(String cpf) {
        try {
            String cpfFormatteed;
            cpfFormatteed = MaskUtils.maskCpf(cpf);
            if(StringUtils.isNotEmpty(cpfFormatteed)) {
                return cpfFormatteed;
            }
            throw new UserCannotBeRegisteredException();
        }
        catch (RuntimeException ex) {
            throw new UserCannotBeRegisteredException(String.format("%s", "CPF is null or invalid."));
        }
    }

    private String getCep(String cep) {
        try {
            String cepFormatteed;
            cepFormatteed = MaskUtils.maskCep(cep);
            if(StringUtils.isNotEmpty(cepFormatteed)) {
                return cepFormatteed;
            }
            throw new UserCannotBeRegisteredException();
        }
        catch (RuntimeException ex) {
            throw new UserCannotBeRegisteredException(String.format("%s", "CEP is null or invalid."));
        }
    }
}