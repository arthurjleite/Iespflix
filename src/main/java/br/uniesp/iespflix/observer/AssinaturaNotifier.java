package br.uniesp.iespflix.observer;

import br.uniesp.iespflix.model.Assinatura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssinaturaNotifier {

    private final List<AssinaturaEventObserver> observers;

    public AssinaturaNotifier(List<AssinaturaEventObserver> observers) {
        this.observers = observers;
    }

    public void notificar(Assinatura assinatura, String evento) {

        for (AssinaturaEventObserver observer : observers) {
            observer.atualizar(assinatura, evento);
        }

    }
}
