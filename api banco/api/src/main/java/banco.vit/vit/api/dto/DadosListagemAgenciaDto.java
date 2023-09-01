package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Agencia;

public record DadosListagemAgenciaDto(
        String nomeAgencia,
        Long id_banco
) {
    public DadosListagemAgenciaDto(Agencia agencia){
        this(agencia.getNomeAgencia(),
                agencia.getBanco().getId()
              );
    }
}
