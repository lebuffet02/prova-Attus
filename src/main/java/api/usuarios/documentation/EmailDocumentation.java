package api.usuarios.documentation;

import api.usuarios.dto.EmailRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Email", description = "Send email to an user")
public interface EmailDocumentation {

    @Operation(operationId = "email", description = "Send Email",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Email was sent"),
                    @ApiResponse(responseCode = "401", description = "Invalid User or Login"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "409", description = "Conflict"),
                    @ApiResponse(responseCode = "500", description = "Internal Error"),
            })
    ResponseEntity<?> sendEmail(@org.springframework.web.bind.annotation.RequestBody EmailRequestDTO emailDTO);
}
