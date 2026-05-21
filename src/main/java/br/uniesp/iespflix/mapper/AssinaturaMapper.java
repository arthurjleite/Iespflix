package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.AssinaturaDTO;
import br.uniesp.iespflix.model.Assinatura;
import br.uniesp.iespflix.model.Plano;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaMapper {

    public AssinaturaDTO toDTO(Assinatura assinatura) {

        if (assinatura == null) {
            return null;
        }

        return AssinaturaDTO.builder()
                .id(assinatura.getId())
                .usuarioId(assinatura.getUsuario().getId())
                .planoId(assinatura.getPlano().getId())
                .status(assinatura.getStatus())
                .iniciadaEm(assinatura.getIniciadaEm())
                .canceladaEm(assinatura.getCanceladaEm())
                .build();
    }

    public Assinatura toEntity(AssinaturaDTO dto) {

        if (dto == null) {
            return null;
        }

        Usuario usuario = Usuario.builder()
                .id(dto.getUsuarioId())
                .build();

        Plano plano = Plano.builder()
                .id(dto.getPlanoId())
                .build();

        return Assinatura.builder()
                .id(dto.getId())
                .usuario(usuario)
                .plano(plano)
                .status(dto.getStatus())
                .iniciadaEm(dto.getIniciadaEm())
                .canceladaEm(dto.getCanceladaEm())
                .build();
    }
}