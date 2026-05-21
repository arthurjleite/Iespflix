package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.model.Favorito;
import br.uniesp.iespflix.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    // Listar todos os favoritos de um usuário específico
    List<Favorito> findByUsuarioId(UUID usuarioId);

    // Verificar se um conteúdo específico já foi favoritado por um usuário
    boolean existsByIdUsuarioIdAndIdConteudoId(UUID usuarioId, UUID conteudoId);
}