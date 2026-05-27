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
import br.uniesp.iespflix.dto.AssinaturaDTO;
import br.uniesp.iespflix.dto.PlanoDTO;
import br.uniesp.iespflix.mapper.AssinaturaMapper;
import br.uniesp.iespflix.mapper.PlanoMapper;
import java.util.stream.Collectors;

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
    private final PlanoMapper planoMapper;
    private final AssinaturaMapper assinaturaMapper;

    public AssinaturaDTO obterAssinaturaAtivaDoUsuario(UUID usuarioId) {

        Assinatura assinatura = assinaturaRepository
                .buscarAssinaturaAtivaDoUsuario(usuarioId)
                .orElse(null);

        return assinaturaMapper.toDTO(assinatura);
    }

    public List<AssinaturaDTO> listarPorPlanoEStatus(String codigoPlano, StatusAssinatura status) {

        return assinaturaRepository.buscarAssinaturasPorPlanoEStatus(codigoPlano, status)
                .stream()
                .map(assinaturaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AssinaturaDTO iniciarAssinatura(UUID usuarioId, UUID planoId) {
        if (assinaturaRepository.buscarAssinaturaAtivaDoUsuario(usuarioId).isPresent()) {
            throw new RuntimeException("O usuário já possui uma assinatura ativa no momento.");
        }

        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(usuarioId);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        PlanoDTO planoDTO = planoService.buscarPorId(planoId);
        Plano plano = planoMapper.toEntity(planoDTO);

        Assinatura assinatura = Assinatura.builder()
                .usuario(usuario)
                .plano(plano)
                .status(StatusAssinatura.ATIVA)
                .iniciadaEm(LocalDateTime.now())
                .build();

        Assinatura assinaturaSalva = assinaturaRepository.save(assinatura);

        return assinaturaMapper.toDTO(assinaturaSalva);
    }

    @Transactional
    public AssinaturaDTO cancelarAssinatura(UUID assinaturaId) {
        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada."));

        if (assinatura.getStatus() == StatusAssinatura.CANCELADA) {
            throw new RuntimeException("Esta assinatura já se encontra cancelada.");
        }

        assinatura.setStatus(StatusAssinatura.CANCELADA);
        assinatura.setCanceladaEm(LocalDateTime.now());

        Assinatura assinaturaAtualizada = assinaturaRepository.save(assinatura);

        return assinaturaMapper.toDTO(assinaturaAtualizada);
    }
}