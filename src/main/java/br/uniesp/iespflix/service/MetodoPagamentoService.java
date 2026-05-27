package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.mapper.UsuarioMapper;
import br.uniesp.iespflix.model.MetodoPagamento;
import br.uniesp.iespflix.model.Usuario;
import br.uniesp.iespflix.repository.MetodoPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.uniesp.iespflix.dto.MetodoPagamentoDTO;
import br.uniesp.iespflix.mapper.MetodoPagamentoMapper;
import java.util.stream.Collectors;

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
    private final MetodoPagamentoMapper metodoPagamentoMapper;

    public List<MetodoPagamentoDTO> listarPorUsuario(UUID usuarioId) {

        return metodoPagamentoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(metodoPagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MetodoPagamentoDTO cadastrar(UUID usuarioId, MetodoPagamentoDTO dto) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(usuarioId);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        MetodoPagamento metodoPagamento = metodoPagamentoMapper.toEntity(dto);

        metodoPagamento.setUsuario(usuario);
        metodoPagamento.setCriadoEm(LocalDateTime.now());

        if (metodoPagamento.getTokenGateway() == null || metodoPagamento.getTokenGateway().isEmpty()) {
            metodoPagamento.setTokenGateway(UUID.randomUUID().toString());
        }

        MetodoPagamento metodoPagamentoSalvo = metodoPagamentoRepository.save(metodoPagamento);

        return metodoPagamentoMapper.toDTO(metodoPagamentoSalvo);
    }
}