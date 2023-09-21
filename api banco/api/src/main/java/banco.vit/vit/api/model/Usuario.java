package banco.vit.vit.api.model;

import banco.vit.vit.api.dto.DadosCadastrosUsuariosDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "usuario")
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Usuario {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String usuario;

    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    private boolean ativo;

//    @Enumerated(EnumType.STRING)
//    private Pais pais;

    public Usuario (DadosCadastrosUsuariosDto dados){
        this.nome = dados.nome();

        this.email = dados.email();

        this.usuario = dados.usuario();

        this.senha = dados.senha();

        this.endereco = new Endereco(dados.endereco());

        this.ativo = true;
//        this.pais =  dados.pais();
    }

    public void excluir (){
        this.ativo = false;
    }

}
