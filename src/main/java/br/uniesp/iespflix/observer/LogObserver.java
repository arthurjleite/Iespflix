package br.uniesp.iespflix.observer;

import br.uniesp.iespflix.model.Assinatura;
import org.springframework.stereotype.Component;

@Component
public class LogObserver implements AssinaturaEventObserver {

    @Override
    public void atualizar(Assinatura assinatura, String evento) {

        System.out.println(
                "[LOG] \nEvento: " + evento +
                        " | Assinatura: " + assinatura.getId() + "\n[LOG]"
        );

    }
}
