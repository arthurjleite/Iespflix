package br.uniesp.iespflix.factory;

import br.uniesp.iespflix.enums.StatusAssinatura;
import br.uniesp.iespflix.model.Assinatura;
import br.uniesp.iespflix.model.Plano;
import br.uniesp.iespflix.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssinaturaFactory {

    public Assinatura criarAssinaturaAtiva(Usuario usuario, Plano plano) {
        System.out.println(
                "\n[FACTORY] Criando assinatura ativa para o usuário: "
                        + usuario.getNomeCompleto()
        );
        return Assinatura.builder()
                .usuario(usuario)
                .plano(plano)
                .status(StatusAssinatura.ATIVA)
                .iniciadaEm(LocalDateTime.now())
                .build();
    }
}
