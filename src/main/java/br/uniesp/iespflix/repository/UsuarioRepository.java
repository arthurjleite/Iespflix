package br.uniesp.iespflix.repository;

import br.uniesp.iespflix.enums.PerfilUsuario;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpfCnpj(String cpfCnpj);

    // CONSULTA JPQL 1: Buscar usuários por perfil específico
    @Query("SELECT u FROM Usuario u WHERE u.perfil = :perfil ORDER BY u.nomeCompleto ASC")
    List<Usuario> buscarPorPerfil(@Param("perfil") PerfilUsuario perfil);
}
