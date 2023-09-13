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
    @Column(name = "id")
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
        this.limiteLis = dados.limiteLis();
    }

    public void excluir() {
        this.ativo = false;
    }
    public void adicionar(Long valor){
        this.saldo +=valor;
    }

    public void transferir (Long valor){
        this.saldo -=valor;
    }

    public void transferirSaldoCredito(double valor){
        this.limiteCredito -= valor;
    }
    public void receberSaldoCredito(Long valor){
        this.limiteCredito += valor;
    }
    public void transferirSaldoLis (double valor){
        this.limiteLis -= valor;
    }
    public void receberSaldoLis (Long valor){
        this.limiteLis += valor;
    }
    public void zerarSaldo(){
        this.saldo = 0L;
    }
    public void zerarSaldoLis(Long valor){
        this.limiteLis = 0L;
    }
    public void zerarCredtio(Long valor){
        this.limiteCredito = 0L;
    }
    public void depositoSaldo(Long valor){
        this.saldo += valor;
    }
}
