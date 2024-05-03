package api.usuarios.documentation;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Email", description = "Send email to an user")
public interface EmailDocumentation {

    @Operation(operationId = "email", description = "Send Email",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Email was sent",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = EmailRequestDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid Permission",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDetails.class))),
            })
    ResponseEntity<?> sendEmail(EmailRequestDTO emailDTO);
}