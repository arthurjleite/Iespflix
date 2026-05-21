package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.ConteudoDTO;
import br.uniesp.iespflix.enums.TipoConteudo;
import br.uniesp.iespflix.mapper.ConteudoMapper;
import br.uniesp.iespflix.model.Conteudo;
import br.uniesp.iespflix.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConteudoService {

    private final ConteudoRepository conteudoRepository;
    private final ConteudoMapper conteudoMapper;

    public List<ConteudoDTO> listarComFiltrosETrailer(TipoConteudo tipo, String genero) {
        return conteudoRepository.listarComFiltrosETrailerDisponivel(tipo, genero)
                .stream()
                .map(conteudoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ConteudoDTO> pesquisarPorTexto(String termo) {
        List<Conteudo> resultados;
        if (termo == null || termo.trim().isEmpty()) {
            resultados = conteudoRepository.findAll();
        } else {
            resultados = conteudoRepository.buscaTextual(termo);
        }
        return resultados.stream()
                .map(conteudoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ConteudoDTO> obterTopN(int quantidade) {
        Pageable limite = PageRequest.of(0, quantidade);
        return conteudoRepository.buscarTopNConteudos(limite)
                .stream()
                .map(conteudoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ConteudoDTO> listarTodos() {
        return conteudoRepository.findAll()
                .stream()
                .map(conteudoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConteudoDTO buscarPorId(UUID id) {
        Conteudo conteudo = conteudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado com o ID fornecido."));
        return conteudoMapper.toDTO(conteudo);
    }

    @Transactional
    public ConteudoDTO salvar(ConteudoDTO dto) {
        Conteudo conteudo = conteudoMapper.toEntity(dto);

        LocalDateTime agora = LocalDateTime.now();
        if (conteudo.getId() == null) {
            conteudo.setCriadoEm(agora);
        } else {
            Conteudo existente = conteudoRepository.findById(conteudo.getId())
                    .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado para atualização."));
            conteudo.setCriadoEm(existente.getCriadoEm());
        }
        conteudo.setAtualizadoEm(agora);

        Conteudo conteudoSalvo = conteudoRepository.save(conteudo);
        return conteudoMapper.toDTO(conteudoSalvo);
    }

    @Transactional
    public void deletar(UUID id) {
        Conteudo conteudo = conteudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado com o ID fornecido."));
        conteudoRepository.delete(conteudo);
    }
}