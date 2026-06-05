package br.uniesp.iespflix.observer;

import br.uniesp.iespflix.model.Assinatura;

public interface AssinaturaEventObserver {

    void atualizar(Assinatura assinatura, String evento);

}