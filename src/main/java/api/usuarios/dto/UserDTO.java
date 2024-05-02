package api.usuarios.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDTO(String nomeCompleto,
                      String email,
                      String cpf,
                      LocalDateTime dataNascimento,
                      AddressDTO addressDTO){}
