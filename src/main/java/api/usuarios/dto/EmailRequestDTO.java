package api.usuarios.dto;

import jakarta.validation.constraints.Pattern;
import java.util.regex.Matcher;

public record EmailRequestDTO(
        @Pattern(regexp = EMAIL_PATTERN)
        String from,
        @Pattern(regexp = EMAIL_PATTERN)
        String to,
        String message,
        String subject) {


    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    private static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile(EMAIL_PATTERN);

    public boolean isEmailValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}