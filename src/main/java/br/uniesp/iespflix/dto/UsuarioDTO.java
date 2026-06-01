package br.uniesp.iespflix.dto;

import br.uniesp.iespflix.enums.PerfilUsuario;
import br.uniesp.iespflix.validation.SenhaForte;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "O nome completo é obrigatório")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    private String nomeCompleto;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 254, message = "O email deve ter no máximo 254 caracteres")
    private String email;

    @Schema(example = "String123", description = "Senha do usuário")
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @SenhaForte
    private String senha;

    @Pattern(
            regexp = "\\d{11}|\\d{14}",
            message = "CPF/CNPJ deve conter 11 ou 14 dígitos"
    )
    private String cpfCnpj;

    @NotNull(message = "O perfil é obrigatório")
    private PerfilUsuario perfil;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime criadoEm;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime atualizadoEm;
}