package br.uniesp.iespflix.observer;

import br.uniesp.iespflix.model.Assinatura;
import org.springframework.stereotype.Component;

@Component
public class EmailObserver implements AssinaturaEventObserver {

    @Override
    public void atualizar(Assinatura assinatura, String evento) {

        String assunto;
        String mensagem;

        if ("ASSINATURA_CRIADA".equals(evento)) {

            assunto = "Assinatura ativada com sucesso";

            mensagem = """
                    
                    Olá, %s!
                    
                    Sua assinatura foi ativada com sucesso.
                    
                    Plano contratado: %s
                    Data de início: %s
                    
                    Aproveite todo o catálogo do IESPFLIX!
                    
                    Equipe IESPFLIX
                    """.formatted(
                    assinatura.getUsuario().getNomeCompleto(),
                    assinatura.getPlano().getCodigo(),
                    assinatura.getIniciadaEm()
            );

        } else {

            assunto = "Assinatura cancelada";

            mensagem = """
                    
                    Olá, %s!
                    
                    Sua assinatura foi cancelada com sucesso.
                    
                    Data de cancelamento: %s
                    
                    Esperamos vê-lo novamente em breve.
                    
                    Equipe IESPFLIX
                    """.formatted(
                    assinatura.getUsuario().getNomeCompleto(),
                    assinatura.getCanceladaEm()
            );
        }
        System.out.println("\n[EMAIL SIMULADO]\n");
        System.out.println("\nAssunto: " + assunto);
        System.out.println(mensagem);
        System.out.println("[EMAIL SIMULADO]\n");
    }
}
