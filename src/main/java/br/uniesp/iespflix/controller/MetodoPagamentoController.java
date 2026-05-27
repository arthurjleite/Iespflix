package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.MetodoPagamentoDTO;
import br.uniesp.iespflix.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/metodos-pagamento")
@RequiredArgsConstructor
public class MetodoPagamentoController {

    private final MetodoPagamentoService metodoPagamentoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<MetodoPagamentoDTO>> listarPorUsuario(
            @PathVariable UUID usuarioId
    ) {

        return ResponseEntity.ok(
                metodoPagamentoService.listarPorUsuario(usuarioId)
        );
    }

    @PostMapping
    public ResponseEntity<MetodoPagamentoDTO> cadastrar(
            @RequestParam UUID usuarioId,
            @Valid @RequestBody MetodoPagamentoDTO dto
    ) {

        return ResponseEntity.ok(
                metodoPagamentoService.cadastrar(usuarioId, dto)
        );
    }
}
