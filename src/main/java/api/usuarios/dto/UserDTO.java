package api.usuarios.dto;

import api.usuarios.exception.UserCannotBeRegisteredException;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import java.util.regex.Matcher;

@Builder
public record UserDTO(
        String nomeCompleto,
        @Pattern(regexp = EMAIL_PATTERN)
        String email,
        String cpf,
        String dataNascimento,
        AddressDTO addressDTO) {

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    private static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile(EMAIL_PATTERN);

    public boolean isEmailValid(final String email) {
        try {
            Matcher matcher = PATTERN.matcher(email);
            if(matcher.matches()) {
                return true;
            }
            throw new UserCannotBeRegisteredException();
        } catch (RuntimeException ex) {
            throw new UserCannotBeRegisteredException(String.format("%s", "Email is invalid."));
        }
    }
}
