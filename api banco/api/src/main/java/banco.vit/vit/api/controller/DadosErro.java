package banco.vit.vit.api.controller;

public class  DadosErro extends RuntimeException{

    public DadosErro (String mensagem){
        super(mensagem);
    }
}


