package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosAtualizacaoBancoDto;
import banco.vit.vit.api.dto.DadosCadastroBancoDto;
import banco.vit.vit.api.dto.DadosListagemBancoDto;
import banco.vit.vit.api.model.Banco;
import banco.vit.vit.api.model.Endereco;
import banco.vit.vit.api.repository.BancoRepository;
import banco.vit.vit.api.repository.EnderecoRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BancoService {
    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrarBanco(DadosCadastroBancoDto dados){
        bancoRepository.save(new Banco(dados));
    }

    public Page<DadosListagemBancoDto> listarBanco(Pageable paginacao){
        return bancoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemBancoDto::new);
    }

    public void atualizarBanco(DadosAtualizacaoBancoDto dadosAtualizar){
        Banco banco = bancoRepository.findById(dadosAtualizar.id()).get();

        if(banco.getNome() != null){
            banco.setNome(dadosAtualizar.nome());
        }

    }

    public void excluirBanco(Long id){
        Banco banco = bancoRepository.getReferenceById(id);
        banco.excluir();
    }
}
