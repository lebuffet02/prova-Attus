package api.usuarios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "UsuarioEntity")
@Table(name = "USUARIOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O nome completo não pode estar vazio")
    private String nomeCompleto;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dataNascimento")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private AddressEntity enderecos;
}
