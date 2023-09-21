package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosCadastroBancoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "banco")
@Table(name = "banco")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Banco {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "banco")
    private List<Agencia> agencias;

//    @Enumerated(EnumType.STRING)
//    private Pais pais;

    public Banco (DadosCadastroBancoDto dados){
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }


    public void excluir(){
        this.ativo = false;
    }

}
