package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.enums.StatusAssinatura;
import br.uniesp.iespflix.mapper.UsuarioMapper;
import br.uniesp.iespflix.model.Assinatura;
import br.uniesp.iespflix.model.Plano;
import br.uniesp.iespflix.model.Usuario;
import br.uniesp.iespflix.repository.AssinaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioService usuarioService;
    private final PlanoService planoService;
    private final UsuarioMapper usuarioMapper;

    public Assinatura obterAssinaturaAtivaDoUsuario(UUID usuarioId) {
        return assinaturaRepository.buscarAssinaturaAtivaDoUsuario(usuarioId)
                .orElse(null);
    }

    public List<Assinatura> listarPorPlanoEStatus(String codigoPlano, StatusAssinatura status) {
        return assinaturaRepository.buscarAssinaturasPorPlanoEStatus(codigoPlano, status);
    }

    @Transactional
    public Assinatura iniciarAssinatura(UUID usuarioId, UUID planoId) {
        if (assinaturaRepository.buscarAssinaturaAtivaDoUsuario(usuarioId).isPresent()) {
            throw new RuntimeException("O usuário já possui uma assinatura ativa no momento.");
        }

        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(usuarioId);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Plano plano = planoService.buscarPorId(planoId);

        Assinatura assinatura = Assinatura.builder()
                .usuario(usuario)
                .plano(plano)
                .status(StatusAssinatura.ATIVA)
                .iniciadaEm(LocalDateTime.now())
                .build();

        return assinaturaRepository.save(assinatura);
    }

    @Transactional
    public Assinatura cancelarAssinatura(UUID assinaturaId) {
        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada."));

        if (assinatura.getStatus() == StatusAssinatura.CANCELADA) {
            throw new RuntimeException("Esta assinatura já se encontra cancelada.");
        }

        assinatura.setStatus(StatusAssinatura.CANCELADA);
        assinatura.setCanceladaEm(LocalDateTime.now());

        return assinaturaRepository.save(assinatura);
    }
}