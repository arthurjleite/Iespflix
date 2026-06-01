package br.uniesp.iespflix.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "O código do plano é obrigatório")
    @Pattern(
            regexp = "BASICO|PADRAO|PREMIUM",
            message = "O código deve ser BASICO, PADRAO ou PREMIUM"
    )
    private String codigo;

    @NotNull(message = "O limite diário é obrigatório")
    @Min(value = 1, message = "O limite diário deve ser no mínimo 1")
    private Short limiteDiario;

    @NotNull(message = "Streams simultâneos é obrigatório")
    @Min(value = 1, message = "Deve haver pelo menos 1 stream simultâneo")
    private Short streamsSimultaneos;
}
