package api.usuarios.service;


import api.usuarios.domain.AuthKeycloak;
import api.usuarios.domain.RefreshKeycloak;
import api.usuarios.dto.auth.AuthKeycloakDTO;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}