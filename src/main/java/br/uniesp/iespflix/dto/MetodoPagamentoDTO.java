package br.uniesp.iespflix.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagamentoDTO {

    private UUID id;

    @NotNull(message = "O usuário é obrigatório")
    private UUID usuarioId;

    @NotBlank(message = "A bandeira do cartão é obrigatória")
    @Size(max = 20, message = "A bandeira deve ter no máximo 20 caracteres")
    private String bandeira;

    @NotBlank(message = "Os últimos 4 dígitos são obrigatórios")
    @Pattern(
            regexp = "\\d{4}",
            message = "Os últimos 4 dígitos devem conter exatamente 4 números"
    )
    private String ultimos4;

    @NotNull(message = "O mês de expiração é obrigatório")
    @Min(value = 1, message = "O mês mínimo é 1")
    @Max(value = 12, message = "O mês máximo é 12")
    private Short mesExp;

    @NotNull(message = "O ano de expiração é obrigatório")
    private Short anoExp;

    @NotBlank(message = "O nome do portador é obrigatório")
    @Size(max = 150, message = "O nome do portador deve ter no máximo 150 caracteres")
    private String nomePortador;

    @NotBlank(message = "O token do gateway é obrigatório")
    @Size(max = 120, message = "O token deve ter no máximo 120 caracteres")
    private String tokenGateway;

    private LocalDateTime criadoEm;
}
