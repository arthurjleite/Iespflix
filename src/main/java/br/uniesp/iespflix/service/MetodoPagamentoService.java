package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.mapper.UsuarioMapper;
import br.uniesp.iespflix.model.MetodoPagamento;
import br.uniesp.iespflix.model.Usuario;
import br.uniesp.iespflix.repository.MetodoPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public List<MetodoPagamento> listarPorUsuario(UUID usuarioId) {
        return metodoPagamentoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public MetodoPagamento cadastrar(UUID usuarioId, MetodoPagamento metodoPagamento) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(usuarioId);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        metodoPagamento.setUsuario(usuario);
        metodoPagamento.setCriadoEm(LocalDateTime.now());

        if (metodoPagamento.getTokenGateway() == null || metodoPagamento.getTokenGateway().isEmpty()) {
            metodoPagamento.setTokenGateway(UUID.randomUUID().toString());
        }

        return metodoPagamentoRepository.save(metodoPagamento);
    }
}