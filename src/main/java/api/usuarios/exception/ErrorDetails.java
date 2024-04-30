package api.usuarios.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorDetails(
        @JsonProperty("error_moment") String errorMoment,
        @JsonProperty("error_description") String errorDescription,
        @JsonProperty("error_type") String errorType,
        @JsonProperty("error_code") String errorCode,
        @JsonProperty("error_address") String errorIp) {
}
