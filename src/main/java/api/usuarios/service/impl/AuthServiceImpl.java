package api.usuarios.service.impl;

import api.usuarios.client.KeycloakClient;
import api.usuarios.domain.AuthKeycloak;
import api.usuarios.domain.RefreshKeycloak;
import api.usuarios.dto.auth.AuthKeycloakDTO;
import api.usuarios.exception.AuthException;
import api.usuarios.exception.RefreshException;
import api.usuarios.mapper.MapperAuthKeycloak;
import api.usuarios.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    KeycloakClient client;
    @Autowired
    MapperAuthKeycloak mapper;


    @Override
    public AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak) {
        try {
            return mapper.builderToAuthKeycloakDTO(getChamadaFeign(authKeycloak));
        } catch (RuntimeException ex) {
            throw new AuthException(String.format("%s", "Error when validating token"));
        }
    }

    @Override
    public AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh) {
        try {
            return mapper.builderToAuthKeycloakDTO(getChamadaFeign(refresh));
        } catch (RuntimeException ex) {
            throw new RefreshException(String.format("%s", "Error Refresh token"));
        }
    }

    private AuthKeycloak getChamadaFeign(Object obj) {
        if (obj instanceof AuthKeycloak authKeycloak) {
            AuthKeycloak auth = client.getDadosKeycloak(getFormTokenData(authKeycloak));
            Objects.requireNonNull(auth);
            return auth;
        }
        RefreshKeycloak refKeycloak = (RefreshKeycloak) obj;
        AuthKeycloak refresh = client.getDadosKeycloak(getFormRefreshData(refKeycloak));
        Objects.requireNonNull(refresh);
        return refresh;
    }

    private MultiValueMap<String, String> getFormTokenData(AuthKeycloak auth) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", auth.clientId());
        formData.add("username", auth.username());
        formData.add("password", auth.password());
        formData.add("grant_type", auth.grantType());
        return formData;
    }

    private MultiValueMap<String, String> getFormRefreshData(RefreshKeycloak refresh) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", refresh.clientId());
        formData.add("refresh_token", refresh.refresh_token());
        formData.add("grant_type", "refresh_token");
        return formData;
    }
}