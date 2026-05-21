package br.uniesp.iespflix.dto;

import br.uniesp.iespflix.enums.StatusAssinatura;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaDTO {

    private UUID id;

    @NotNull(message = "O usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "O plano é obrigatório")
    private UUID planoId;

    @NotNull(message = "O status da assinatura é obrigatório")
    private StatusAssinatura status;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime iniciadaEm;

    private LocalDateTime canceladaEm;
}
