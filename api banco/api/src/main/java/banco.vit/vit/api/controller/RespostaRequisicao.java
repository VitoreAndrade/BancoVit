package banco.vit.vit.api.controller;

public class RespostaRequisicao {

    private String mensagem;
    private String codigo;

    public RespostaRequisicao(String mensagem, String codigo){
        this.mensagem = mensagem;
        this.codigo = codigo;
    }
}
