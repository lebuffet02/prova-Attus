package api.usuarios.controller;

import api.usuarios.client.KeycloakClient;
import api.usuarios.domain.AuthKeycloak;
import api.usuarios.domain.RefreshKeycloak;
import api.usuarios.dto.auth.AuthKeycloakDTO;
import api.usuarios.service.AuthService;
import api.usuarios.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    KeycloakClient client;
    @Autowired
    AuthServiceImpl serviceImpl;


    @PostMapping("/token")
    public ResponseEntity<AuthKeycloakDTO> token(@RequestBody AuthKeycloak authKeycloak) {
        return ResponseEntity.ok(serviceImpl.tokenService(authKeycloak));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthKeycloakDTO> refreshToken(@RequestBody RefreshKeycloak refresh) {
        return ResponseEntity.ok(serviceImpl.refreshTokenService(refresh));
    }
}