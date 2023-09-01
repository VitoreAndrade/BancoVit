package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.model.TipoDeConta;

public record DadosAtualizacaoContaDto(
        Long id,
        TipoDeConta tipoDeConta,
        double saldo
) {

}
