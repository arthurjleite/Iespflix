package br.uniesp.iespflix.dto;

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
public class FavoritoDTO {

    @NotNull(message = "O usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "O conteúdo é obrigatório")
    private UUID conteudoId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime criadoEm;
}
