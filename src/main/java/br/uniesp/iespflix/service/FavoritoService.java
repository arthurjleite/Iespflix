package br.uniesp.iespflix.service;

import br.uniesp.iespflix.dto.ConteudoDTO;
import br.uniesp.iespflix.dto.UsuarioDTO;
import br.uniesp.iespflix.mapper.ConteudoMapper;
import br.uniesp.iespflix.mapper.UsuarioMapper;
import br.uniesp.iespflix.model.Conteudo;
import br.uniesp.iespflix.model.Favorito;
import br.uniesp.iespflix.model.FavoritoId;
import br.uniesp.iespflix.model.Usuario;
import br.uniesp.iespflix.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.uniesp.iespflix.dto.FavoritoDTO;
import br.uniesp.iespflix.mapper.FavoritoMapper;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioService usuarioService;
    private final ConteudoService conteudoService;
    private final UsuarioMapper usuarioMapper;
    private final ConteudoMapper conteudoMapper;
    private final FavoritoMapper favoritoMapper;

    public List<FavoritoDTO> listarFavoritosDoUsuario(UUID usuarioId) {

        return favoritoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(favoritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FavoritoDTO favoritar(UUID usuarioId, UUID conteudoId) {
        if (favoritoRepository.existsByIdUsuarioIdAndIdConteudoId(usuarioId, conteudoId)) {
            throw new RuntimeException("Este conteúdo já está na lista de favoritos do usuário.");
        }

        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(usuarioId);
        ConteudoDTO conteudoDTO = conteudoService.buscarPorId(conteudoId);

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Conteudo conteudo = conteudoMapper.toEntity(conteudoDTO);

        FavoritoId idComposto = new FavoritoId(usuarioId, conteudoId);

        Favorito favorito = Favorito.builder()
                .id(idComposto)
                .usuario(usuario)
                .conteudo(conteudo)
                .criadoEm(LocalDateTime.now())
                .build();

        Favorito favoritoSalvo = favoritoRepository.save(favorito);
        return favoritoMapper.toDTO(favoritoSalvo);
    }

    @Transactional
    public void removerDosFavoritos(UUID usuarioId, UUID conteudoId) {
        FavoritoId idComposto = new FavoritoId(usuarioId, conteudoId);
        Favorito favorito = favoritoRepository.findById(idComposto)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado para remoção."));
        favoritoRepository.delete(favorito);
    }
}