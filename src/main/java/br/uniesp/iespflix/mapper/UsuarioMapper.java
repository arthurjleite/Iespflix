package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {

        if (usuario == null) {
            return null;
        }

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nomeCompleto(usuario.getNomeCompleto())
                .dataNascimento(usuario.getDataNascimento())
                .email(usuario.getEmail())
                .cpfCnpj(usuario.getCpfCnpj())
                .perfil(usuario.getPerfil())
                .criadoEm(usuario.getCriadoEm())
                .atualizadoEm(usuario.getAtualizadoEm())
                .build();
    }

    public Usuario toEntity(UsuarioDTO dto) {

        if (dto == null) {
            return null;
        }

        return Usuario.builder()
                .id(dto.getId())
                .nomeCompleto(dto.getNomeCompleto())
                .dataNascimento(dto.getDataNascimento())
                .email(dto.getEmail())
                .senhaHash(dto.getSenha())
                .cpfCnpj(dto.getCpfCnpj())
                .perfil(dto.getPerfil())
                .criadoEm(dto.getCriadoEm())
                .atualizadoEm(dto.getAtualizadoEm())
                .build();
    }
}
