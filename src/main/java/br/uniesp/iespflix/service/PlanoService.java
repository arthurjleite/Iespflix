package br.uniesp.iespflix.service;

import br.uniesp.iespflix.model.Plano;
import br.uniesp.iespflix.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanoService {

    private final PlanoRepository planoRepository;

    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public Plano buscarPorId(UUID id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado com o ID fornecido."));
    }

    public Plano buscarPorCodigo(String codigo) {
        return planoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado com o código: " + codigo));
    }

    @Transactional
    public Plano salvar(Plano plano) {
        if (planoRepository.findByCodigo(plano.getCodigo()).isPresent() && plano.getId() == null) {
            throw new RuntimeException("Já existe um plano cadastrado com este código.");
        }
        return planoRepository.save(plano);
    }
}