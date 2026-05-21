package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, UUID> {

    // Buscar todos os métodos de pagamento cadastrados por um usuário
    List<MetodoPagamento> findByUsuarioId(UUID usuarioId);
}