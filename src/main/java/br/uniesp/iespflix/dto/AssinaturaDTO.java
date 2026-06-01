package br.uniesp.iespflix.dto;

import br.uniesp.iespflix.enums.StatusAssinatura;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotNull(message = "O usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "O plano é obrigatório")
    private UUID planoId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private StatusAssinatura status;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime iniciadaEm;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime canceladaEm;
}
