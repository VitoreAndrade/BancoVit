package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosAtualizacaoAgenciaDto;
import banco.vit.vit.api.dto.DadosCadastroAgenciaDto;
import banco.vit.vit.api.dto.DadosListagemAgenciaDto;
import banco.vit.vit.api.dto.DadosListagemUsuarioDto;
import banco.vit.vit.api.model.Agencia;
import banco.vit.vit.api.model.Banco;
import banco.vit.vit.api.repository.AgenciaRepository;
import banco.vit.vit.api.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private BancoRepository bancoRepository;

    public void cadastrarAgencia (DadosCadastroAgenciaDto dados){

        Banco banco = bancoRepository.getReferenceById(dados.id_banco());
        Agencia ag = new Agencia();
        ag.setNomeAgencia(dados.nomeAgencia());
        ag.setBanco(banco);
        if(banco.isAtivo()== true){
            agenciaRepository.save(ag);
        }
    }

    public Page<DadosListagemAgenciaDto> listarAgencia(Pageable pagina){
        return agenciaRepository.findAllByAtivoTrue(pagina).map(DadosListagemAgenciaDto::new);
    }

    public void atualizarAgencia (DadosAtualizacaoAgenciaDto dados){
        Agencia agencia = agenciaRepository.findById(dados.id()).get();

        if(agencia.getNomeAgencia() != null){
            agencia.setNomeAgencia(dados.nomeAgencia());
        }
    }
    public void excluirAgencia(Long id){
        Agencia agencia = agenciaRepository.getReferenceById(id);
        agencia.excluirAgencia();
    }
}
