package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.PlanoDTO;
import br.uniesp.iespflix.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarTodos() {

        return ResponseEntity.ok(
                planoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                planoService.buscarPorId(id)
        );
    }

    @GetMapping("/codigo")
    public ResponseEntity<PlanoDTO> buscarPorCodigo(
            @RequestParam String codigo
    ) {

        return ResponseEntity.ok(
                planoService.buscarPorCodigo(codigo)
        );
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> salvar(
            @Valid @RequestBody PlanoDTO dto
    ) {

        return ResponseEntity.ok(
                planoService.salvar(dto)
        );
    }
}
