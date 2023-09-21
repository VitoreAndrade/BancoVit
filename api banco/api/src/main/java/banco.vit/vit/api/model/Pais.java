package banco.vit.vit.api.model;

public enum Pais {
    BRA("Brasil", Moeda.BRL),
    USA("Estados Unidos da America", Moeda.USD),
    CH("Suiça", Moeda.CHF),
    EUR("Espanha", Moeda.EUR);


    private String nome;
    private Moeda moeda;

     Pais(String nome, Moeda moeda){
        this.nome = nome;
        this.moeda = moeda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }
}
