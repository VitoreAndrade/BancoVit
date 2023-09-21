package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.model.Moeda;
import banco.vit.vit.api.model.TipoDeConta;

public record DadosListagemContaDto(Long id, Long idUsario, Long idBanco, Long idAgencia, TipoDeConta tipoDeConta,
                                    Moeda moeda, double saldo
) {

    public DadosListagemContaDto(Conta conta) {
        this(conta.getId(), conta.getUsario().getId(), conta.getBanco().getId(),
                conta.getAgencia().getId(), conta.getTipoConta(), conta.getMoeda(),
                conta.getSaldo() / conta.getMoeda().getMultiplicador()
        );
    }

    public void atualizarSaldoCotado(Conta conta) {
        double valorFinal = conta.getSaldo() / conta.getMoeda().getMultiplicador();
    }
}
