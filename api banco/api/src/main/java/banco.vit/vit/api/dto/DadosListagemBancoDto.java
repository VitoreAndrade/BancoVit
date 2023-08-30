package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Banco;

public record DadosListagemBancoDto(
        Long id,
        String nome
) {
    public DadosListagemBancoDto(Banco banco){
        this(banco.getId(),
        banco.getNome());
    }
}
