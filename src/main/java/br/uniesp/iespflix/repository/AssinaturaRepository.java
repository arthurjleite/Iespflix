package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.enums.StatusAssinatura;
import br.uniesp.iespflix.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

    // CONSULTA JPQL 3: Buscar a assinatura ativa de um usuário específico
    @Query("SELECT a FROM Assinatura a WHERE a.usuario.id = :usuarioId AND a.status = br.uniesp.iespflix.enums.StatusAssinatura.ATIVA")
    Optional<Assinatura> buscarAssinaturaAtivaDoUsuario(@Param("usuarioId") UUID usuarioId);

    // CONSULTA JPQL 4: Buscar assinaturas filtradas por código do plano e status
    @Query("SELECT a FROM Assinatura a WHERE a.plano.codigo = :codigoPlano AND a.status = :status")
    List<Assinatura> buscarAssinaturasPorPlanoEStatus(
            @Param("codigoPlano") String codigoPlano,
            @Param("status") StatusAssinatura status
    );
}