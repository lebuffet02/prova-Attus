package api.usuarios.domain;

import lombok.Builder;

@Builder
public record RefreshKeycloak(String clientId, String refresh_token){}
