package api.usuarios.service;


import api.usuarios.form.AuthKeycloak;
import api.usuarios.form.RefreshKeycloak;
import api.usuarios.dto.auth.AuthKeycloakDTO;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}