package br.uniesp.iespflix.controller;

import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.enums.PerfilUsuario;
import br.uniesp.iespflix.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {

        return ResponseEntity.ok(
                usuarioService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id)
        );
    }

    @GetMapping("/perfil")
    public ResponseEntity<List<UsuarioDTO>> listarPorPerfil(
            @RequestParam PerfilUsuario perfil
    ) {

        return ResponseEntity.ok(
                usuarioService.listarPorPerfil(perfil)
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(
            @Valid @RequestBody UsuarioDTO dto
    ) {

        return ResponseEntity.ok(
                usuarioService.cadastrar(dto)
        );
    }
}
