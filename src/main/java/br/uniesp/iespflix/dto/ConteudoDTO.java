package br.uniesp.iespflix.dto;

import br.uniesp.iespflix.enums.TipoConteudo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200, message = "O título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotNull(message = "O tipo é obrigatório")
    private TipoConteudo tipo;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1888, message = "O ano deve ser maior ou igual a 1888")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100")
    private Short ano;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração mínima é 1 minuto")
    @Max(value = 999, message = "A duração máxima é 999 minutos")
    private Short duracaoMinutos;

    @NotNull(message = "A relevância é obrigatória")
    @DecimalMin(value = "0.00", message = "A relevância mínima é 0")
    @DecimalMax(value = "99.99", message = "A relevância máxima é 99.99")
    private BigDecimal relevancia;

    private String sinopse;

    @Size(max = 500, message = "A URL do trailer deve ter no máximo 500 caracteres")
    private String trailerUrl;

    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres")
    private String genero;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime criadoEm;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime atualizadoEm;
}
