package br.uniesp.iespflix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FavoritoId {

    @Column(name = "usuario_id")
    private UUID usuarioId;

    @Column(name = "conteudo_id")
    private UUID conteudoId;
}
