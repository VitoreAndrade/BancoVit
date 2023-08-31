package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosCadastroAgenciaDto;
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
        agenciaRepository.save(new Agencia(dados));
    }
}
