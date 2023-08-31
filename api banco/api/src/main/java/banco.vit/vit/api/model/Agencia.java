package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosCadastroAgenciaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "agencia")
@Table(name = "agencia")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long id_banco;

    private String nomeAgencia;

    @ManyToOne
    @JoinColumn(name = "id_banco", nullable = false )
    private Banco banco;

    public Agencia (DadosCadastroAgenciaDto dados){
//        this.id_banco = dados.id_banco();
        this.nomeAgencia = dados.nomeAgencia();
    }
}
