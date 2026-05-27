package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.ConteudoDTO;
import br.uniesp.iespflix.enums.TipoConteudo;
import br.uniesp.iespflix.service.ConteudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/conteudos")
@RequiredArgsConstructor
public class ConteudoController {

    private final ConteudoService conteudoService;

    @GetMapping
    public ResponseEntity<List<ConteudoDTO>> listarTodos() {

        return ResponseEntity.ok(conteudoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoDTO> buscarPorId(@PathVariable UUID id) {

        return ResponseEntity.ok(conteudoService.buscarPorId(id));
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<ConteudoDTO>> listarComFiltros(
            @RequestParam(required = false) TipoConteudo tipo,
            @RequestParam(required = false) String genero
    ) {

        return ResponseEntity.ok(
                conteudoService.listarComFiltrosETrailer(tipo, genero)
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ConteudoDTO>> buscarPorTexto(
            @RequestParam String termo
    ) {

        return ResponseEntity.ok(
                conteudoService.pesquisarPorTexto(termo)
        );
    }

    @GetMapping("/top")
    public ResponseEntity<List<ConteudoDTO>> topConteudos(
            @RequestParam(defaultValue = "5") int quantidade
    ) {

        return ResponseEntity.ok(
                conteudoService.obterTopN(quantidade)
        );
    }

    @PostMapping
    public ResponseEntity<ConteudoDTO> salvar(
            @Valid @RequestBody ConteudoDTO dto
    ) {

        return ResponseEntity.ok(
                conteudoService.salvar(dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        conteudoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}