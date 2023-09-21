package banco.vit.vit.api.model;

import lombok.Getter;
import lombok.Setter;


public enum Moeda {

    BRL("REAL", "R$", 1),
    USD("DOLAR AMERICANO", "US$",4.87f),
    EUR("EURO", "€", 5.19f),
    CHF("FRANCO SUIÇO","SFr",5.43f);

    private final String nome;
    private final String simbolo;
    private final float multiplicador;
    Moeda (String nome, String simbolo, float multiplicador){
        this.nome = nome;
        this.simbolo = simbolo;
        this.multiplicador = multiplicador;
    }

    public String getNome() {
        return nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public float getMultiplicador() {
        return multiplicador;
    }
}
