package api.usuarios.documentation;

import api.usuarios.dto.UserDTO;
import api.usuarios.entity.UserEntity;
import api.usuarios.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

@Tag(name = "User", description = "Crud about user")
public interface UserDocumentation {

    @Operation(operationId = "saveUser", description = "Save an User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User was saved",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<Optional<UserDTO>> saveUser(UserDTO userDTO);


    @Operation(operationId = "updateUser", description = "Update an User",
            requestBody = @RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User was updated",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<Optional<UserDTO>> updateUser(Long id, UserDTO userDTO);


    @Operation(operationId = "getAllUsers", description = "Get All Users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All users present",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<List<UserDTO>> getAllUsers();


    @Operation(operationId = "getUserById", description = "Get User by Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User is present",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<Optional<UserDTO>> getUserById(Long id);


    @Operation(operationId = "deleteAllUsers", description = "Delete All Users",
            responses = {
                    @ApiResponse(responseCode = "204", description = "All Users were deleted",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<?> deleteAllUsers();

    @Operation(operationId = "deleteUserById", description = "Delete User by Id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User was deleted",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class))),
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
    ResponseEntity<?> deleteUserById(@PathVariable("id") Long id);
}
