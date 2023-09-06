package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosCadastroContaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "conta")
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agencia")
    private Agencia agencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_banco")
    private Banco banco;

    private double saldo;
    @Enumerated(EnumType.STRING)
    private TipoDeConta tipoConta;

    private boolean ativo;
    private boolean cartaoDeCredito;
    private double limiteCredito;
    private boolean lis;
    private double limiteLis;
    public Conta(DadosCadastroContaDto dados) {
        this.tipoConta = dados.tipoDeConta();
        this.saldo = dados.saldo();
        this.cartaoDeCredito = true;
        this.ativo = true;
        this.limiteCredito = dados.limiteCredito();
    }

    public void excluir() {
        this.ativo = false;
    }
}
