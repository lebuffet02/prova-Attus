package api.usuarios.form;

import lombok.Builder;

@Builder
public record RefreshKeycloak(String clientId, String refresh_token){}
