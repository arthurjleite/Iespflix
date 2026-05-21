package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.MetodoPagamentoDTO;
import br.uniesp.iespflix.model.MetodoPagamento;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MetodoPagamentoMapper {

    public MetodoPagamentoDTO toDTO(MetodoPagamento metodoPagamento) {

        if (metodoPagamento == null) {
            return null;
        }

        return MetodoPagamentoDTO.builder()
                .id(metodoPagamento.getId())
                .usuarioId(metodoPagamento.getUsuario().getId())
                .bandeira(metodoPagamento.getBandeira())
                .ultimos4(metodoPagamento.getUltimos4())
                .mesExp(metodoPagamento.getMesExp())
                .anoExp(metodoPagamento.getAnoExp())
                .nomePortador(metodoPagamento.getNomePortador())
                .tokenGateway(metodoPagamento.getTokenGateway())
                .criadoEm(metodoPagamento.getCriadoEm())
                .build();
    }

    public MetodoPagamento toEntity(MetodoPagamentoDTO dto) {

        if (dto == null) {
            return null;
        }

        Usuario usuario = Usuario.builder()
                .id(dto.getUsuarioId())
                .build();

        return MetodoPagamento.builder()
                .id(dto.getId())
                .usuario(usuario)
                .bandeira(dto.getBandeira())
                .ultimos4(dto.getUltimos4())
                .mesExp(dto.getMesExp())
                .anoExp(dto.getAnoExp())
                .nomePortador(dto.getNomePortador())
                .tokenGateway(dto.getTokenGateway())
                .criadoEm(dto.getCriadoEm())
                .build();
    }
}
