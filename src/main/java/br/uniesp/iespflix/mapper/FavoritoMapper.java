package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.FavoritoDTO;
import br.uniesp.iespflix.model.Conteudo;
import br.uniesp.iespflix.model.Favorito;
import br.uniesp.iespflix.model.FavoritoId;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {

    public FavoritoDTO toDTO(Favorito favorito) {

        if (favorito == null) {
            return null;
        }

        return FavoritoDTO.builder()
                .usuarioId(favorito.getUsuario().getId())
                .conteudoId(favorito.getConteudo().getId())
                .criadoEm(favorito.getCriadoEm())
                .build();
    }

    public Favorito toEntity(FavoritoDTO dto) {

        if (dto == null) {
            return null;
        }

        Usuario usuario = Usuario.builder()
                .id(dto.getUsuarioId())
                .build();

        Conteudo conteudo = Conteudo.builder()
                .id(dto.getConteudoId())
                .build();

        FavoritoId favoritoId = FavoritoId.builder()
                .usuarioId(dto.getUsuarioId())
                .conteudoId(dto.getConteudoId())
                .build();

        return Favorito.builder()
                .id(favoritoId)
                .usuario(usuario)
                .conteudo(conteudo)
                .criadoEm(dto.getCriadoEm())
                .build();
    }
}
