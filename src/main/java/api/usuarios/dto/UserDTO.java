package api.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.regex.Matcher;

@Builder
public record UserDTO(
        @NotBlank
        String nomeCompleto,
        @NotBlank
        @Pattern(regexp = EMAIL_PATTERN)
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,
        @NotBlank
        AddressDTO addressDTO) {

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    private static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile(EMAIL_PATTERN);

    public boolean isEmailValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
