package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.enums.PerfilUsuario;
import br.uniesp.iespflix.mapper.UsuarioMapper;
import br.uniesp.iespflix.model.Usuario;
import br.uniesp.iespflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listarPorPerfil(PerfilUsuario perfil) {
        return usuarioRepository.buscarPorPerfil(perfil)
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO cadastrar(UsuarioDTO dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema.");
        }

        if (dto.getCpfCnpj() != null &&
                usuarioRepository.findByCpfCnpj(dto.getCpfCnpj()).isPresent()) {
            throw new RuntimeException("Este CPF/CNPJ já está cadastrado no sistema.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        usuario.setSenhaHash(senhaCriptografada);

        LocalDateTime agora = LocalDateTime.now();
        usuario.setCriadoEm(agora);
        usuario.setAtualizadoEm(agora);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuarioSalvo);
    }
}