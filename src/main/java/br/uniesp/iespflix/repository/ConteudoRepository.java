package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.enums.TipoConteudo;
import br.uniesp.iespflix.model.Conteudo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    // 1. FILTROS + ORDENAÇÃO + TRAILER NÃO NULO (via JPQL Dinâmica)
    // Filtra por tipo/gênero se forem enviados, garante que o trailer não seja nulo/vazio e ordena alfabeticamente
    @Query("SELECT c FROM Conteudo c WHERE " +
            "(:tipo IS NULL OR c.tipo = :tipo) AND " +
            "(:genero IS NULL OR LOWER(c.genero) = LOWER(:genero)) AND " +
            "c.trailerUrl IS NOT NULL AND c.trailerUrl <> '' " +
            "ORDER BY c.titulo ASC")
    List<Conteudo> listarComFiltrosETrailerDisponivel(
            @Param("tipo") TipoConteudo tipo,
            @Param("genero") String genero
    );

    // 2. BUSCA TEXTUAL (via JPQL com LIKE)
    // Busca conteúdos onde o título ou a sinopse contenham o termo digitado (case-insensitive)
    @Query("SELECT c FROM Conteudo c WHERE " +
            "LOWER(c.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.sinopse) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "ORDER BY c.titulo ASC")
    List<Conteudo> buscaTextual(@Param("termo") String termo);

    // 3. TOP N (via JPQL + Pageable)
    // Retorna os N conteúdos com maior relevância. O limite "N" é definido passando o Pageable no Service/Controller.
    @Query("SELECT c FROM Conteudo c ORDER BY c.relevancia DESC")
    List<Conteudo> buscarTopNConteudos(Pageable pageable);
}