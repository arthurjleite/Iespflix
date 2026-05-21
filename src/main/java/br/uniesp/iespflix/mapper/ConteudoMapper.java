package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.ConteudoDTO;
import br.uniesp.iespflix.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoMapper {

    public ConteudoDTO toDTO(Conteudo conteudo) {

        if (conteudo == null) {
            return null;
        }

        return ConteudoDTO.builder()
                .id(conteudo.getId())
                .titulo(conteudo.getTitulo())
                .tipo(conteudo.getTipo())
                .ano(conteudo.getAno())
                .duracaoMinutos(conteudo.getDuracaoMinutos())
                .relevancia(conteudo.getRelevancia())
                .sinopse(conteudo.getSinopse())
                .trailerUrl(conteudo.getTrailerUrl())
                .genero(conteudo.getGenero())
                .criadoEm(conteudo.getCriadoEm())
                .atualizadoEm(conteudo.getAtualizadoEm())
                .build();
    }

    public Conteudo toEntity(ConteudoDTO dto) {

        if (dto == null) {
            return null;
        }

        return Conteudo.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .tipo(dto.getTipo())
                .ano(dto.getAno())
                .duracaoMinutos(dto.getDuracaoMinutos())
                .relevancia(dto.getRelevancia())
                .sinopse(dto.getSinopse())
                .trailerUrl(dto.getTrailerUrl())
                .genero(dto.getGenero())
                .criadoEm(dto.getCriadoEm())
                .atualizadoEm(dto.getAtualizadoEm())
                .build();
    }
}