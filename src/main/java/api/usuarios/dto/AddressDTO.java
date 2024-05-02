package api.usuarios.dto;

import lombok.Builder;

@Builder
public record AddressDTO(String estado, String cidade, String logradouro, String cep, String numero){}
