package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.model.TipoDeConta;

public record DadosListagemContaDto (
        Long id,
        Long idUsario,
        Long idBanco,
        Long idAgencia,
        TipoDeConta tipoDeConta,
        double saldo

) {
    public DadosListagemContaDto(Conta conta){
        this(conta.getId(),
                conta.getUsario().getId(),
                conta.getBanco().getId(),
                conta.getAgencia().getId(),
                conta.getTipoConta(),
                conta.getSaldo());
    }
}
