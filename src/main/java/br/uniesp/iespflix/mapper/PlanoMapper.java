package br.uniesp.iespflix.mapper;

import br.uniesp.iespflix.dto.PlanoDTO;
import br.uniesp.iespflix.model.Plano;
import org.springframework.stereotype.Component;

@Component
public class PlanoMapper {

    public PlanoDTO toDTO(Plano plano) {

        if (plano == null) {
            return null;
        }

        return PlanoDTO.builder()
                .id(plano.getId())
                .codigo(plano.getCodigo())
                .limiteDiario(plano.getLimiteDiario())
                .streamsSimultaneos(plano.getStreamsSimultaneos())
                .build();
    }

    public Plano toEntity(PlanoDTO dto) {

        if (dto == null) {
            return null;
        }

        return Plano.builder()
                .id(dto.getId())
                .codigo(dto.getCodigo())
                .limiteDiario(dto.getLimiteDiario())
                .streamsSimultaneos(dto.getStreamsSimultaneos())
                .build();
    }
}