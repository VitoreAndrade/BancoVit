package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosEnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "endereco")
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Endereco {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String numero;

    public Endereco (DadosEnderecoDto dados){
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
    }
}
