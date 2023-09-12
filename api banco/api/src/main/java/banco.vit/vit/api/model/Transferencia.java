package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosTransferenciaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "transferencia")
@Table(name = "transferencia")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transferencia {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta_envia")
    private Conta contaEnvia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta_recebe")
    private Conta contaRecebe;

    private Long valor;

    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    private StatusTransferencia status;

    @Enumerated(EnumType.STRING)
    private TipoTransferencia tipoTransferencia;

public Transferencia (DadosTransferenciaDto dados){
    this.valor = dados.valor();
    this.status = dados.status();

}



}
