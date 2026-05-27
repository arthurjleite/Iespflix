package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.AssinaturaDTO;
import br.uniesp.iespflix.enums.StatusAssinatura;
import br.uniesp.iespflix.service.AssinaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<AssinaturaDTO> obterAssinaturaAtivaDoUsuario(
            @PathVariable UUID usuarioId
    ) {

        return ResponseEntity.ok(
                assinaturaService.obterAssinaturaAtivaDoUsuario(usuarioId)
        );
    }

    @GetMapping
    public ResponseEntity<List<AssinaturaDTO>> listarPorPlanoEStatus(
            @RequestParam String codigoPlano,
            @RequestParam StatusAssinatura status
    ) {

        return ResponseEntity.ok(
                assinaturaService.listarPorPlanoEStatus(codigoPlano, status)
        );
    }

    @PostMapping
    public ResponseEntity<AssinaturaDTO> iniciarAssinatura(
            @RequestParam UUID usuarioId,
            @RequestParam UUID planoId
    ) {

        return ResponseEntity.ok(
                assinaturaService.iniciarAssinatura(usuarioId, planoId)
        );
    }

    @PatchMapping("/{assinaturaId}/cancelar")
    public ResponseEntity<AssinaturaDTO> cancelarAssinatura(
            @PathVariable UUID assinaturaId
    ) {

        return ResponseEntity.ok(
                assinaturaService.cancelarAssinatura(assinaturaId)
        );
    }
}
