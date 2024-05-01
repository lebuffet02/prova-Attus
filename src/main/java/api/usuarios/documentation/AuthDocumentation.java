package api.usuarios.documentation;

import api.usuarios.domain.AuthKeycloak;
import api.usuarios.domain.RefreshKeycloak;
import api.usuarios.dto.auth.AuthKeycloakDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "AccessToken", description = "Generate token and refresh token by keycloak", externalDocs = @ExternalDocumentation(
        description = "documentation keycloak", url = "https://www.keycloak.org/documentation"
))
public interface AuthDocumentation {

    @Operation(operationId = "auth", description = "Authenticate The User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Generates Bearer token for specified login"),
                    @ApiResponse(responseCode = "401", description = "Invalid User or Login"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "409", description = "Conflict"),
                    @ApiResponse(responseCode = "500", description = "Internal Error"),
            })
        ResponseEntity<AuthKeycloakDTO> token(AuthKeycloak authKeycloak);


    @Operation(operationId = "refresh auth", description = "Refresh User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Generates Bearer token for specified login"),
                    @ApiResponse(responseCode = "401", description = "Invalid User or Login"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "409", description = "Conflict"),
                    @ApiResponse(responseCode = "500", description = "Internal Error"),
            })
    ResponseEntity<AuthKeycloakDTO> refreshToken(RefreshKeycloak refresh);
}
