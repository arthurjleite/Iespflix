package br.uniesp.iespflix.dto;

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

    private LocalDateTime criadoEm;
}
