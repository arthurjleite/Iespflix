package br.uniesp.iespflix.service;

import br.uniesp.iespflix.model.Plano;
import br.uniesp.iespflix.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.uniesp.iespflix.dto.PlanoDTO;
import br.uniesp.iespflix.mapper.PlanoMapper;
import java.util.stream.Collectors;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final PlanoMapper planoMapper;

    public List<PlanoDTO> listarTodos() {
        return planoRepository.findAll()
                .stream()
                .map(planoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlanoDTO buscarPorId(UUID id) {

        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado com o ID fornecido."));

        return planoMapper.toDTO(plano);
    }

    public PlanoDTO buscarPorCodigo(String codigo) {

        Plano plano = planoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado com o código: " + codigo));

        return planoMapper.toDTO(plano);
    }


    @Transactional
    public PlanoDTO salvar(PlanoDTO dto) {
    if (planoRepository.findByCodigo(dto.getCodigo()).isPresent() && dto.getId() == null) {
        throw new RuntimeException("Já existe um plano cadastrado com este código.");
    }
    Plano plano = planoMapper.toEntity(dto);
    Plano planoSalvo = planoRepository.save(plano);

    return planoMapper.toDTO(planoSalvo);
    }
}