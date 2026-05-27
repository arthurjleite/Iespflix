package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.FavoritoDTO;
import br.uniesp.iespflix.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<FavoritoDTO>> listarFavoritosDoUsuario(
            @PathVariable UUID usuarioId
    ) {

        return ResponseEntity.ok(
                favoritoService.listarFavoritosDoUsuario(usuarioId)
        );
    }

    @PostMapping
    public ResponseEntity<FavoritoDTO> favoritar(
            @RequestParam UUID usuarioId,
            @RequestParam UUID conteudoId
    ) {

        return ResponseEntity.ok(
                favoritoService.favoritar(usuarioId, conteudoId)
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> removerDosFavoritos(
            @RequestParam UUID usuarioId,
            @RequestParam UUID conteudoId
    ) {

        favoritoService.removerDosFavoritos(usuarioId, conteudoId);

        return ResponseEntity.noContent().build();
    }
}
