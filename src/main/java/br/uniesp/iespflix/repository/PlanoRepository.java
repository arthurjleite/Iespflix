package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, UUID> {

    Optional<Plano> findByCodigo(String codigo);
}