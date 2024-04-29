package api.usuarios.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorDetails(
        @JsonProperty("error_moment") String moment,
        @JsonProperty("error_description") String errorDescription,
        @JsonProperty("error_code") String errorCode) {
}
