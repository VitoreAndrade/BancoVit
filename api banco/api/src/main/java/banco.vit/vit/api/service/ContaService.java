package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosCadastroContaDto;
import banco.vit.vit.api.model.Agencia;
import banco.vit.vit.api.model.Banco;
import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.model.Usuario;
import banco.vit.vit.api.repository.AgenciaRepository;
import banco.vit.vit.api.repository.BancoRepository;
import banco.vit.vit.api.repository.ContaRepository;
import banco.vit.vit.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

//    Banco banco = bancoRepository.getReferenceById(dados.id_banco());
//    Agencia ag = new Agencia();
//        ag.setNomeAgencia(dados.nomeAgencia());
//        ag.setBanco(banco);
    public void cadastarConta (DadosCadastroContaDto dados){

        Banco banco = bancoRepository.getReferenceById(dados.idBanco());
        Agencia agencia = agenciaRepository.getReferenceById(dados.idAgencia());
        Usuario usuario = usuarioRepository.getReferenceById(dados.IdUsuario());

        Conta conta = new Conta();
        conta.setBanco(banco);
        conta.setAgencia(agencia);
        conta.setUsario(usuario);
        contaRepository.save(new Conta(dados));

    }
}
