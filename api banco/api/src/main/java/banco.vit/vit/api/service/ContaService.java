package banco.vit.vit.api.service;

import banco.vit.vit.api.controller.DadosErro;
import banco.vit.vit.api.dto.DadosAtualizacaoContaDto;
import banco.vit.vit.api.dto.DadosCadastroContaDto;
import banco.vit.vit.api.dto.DadosListagemContaDto;
import banco.vit.vit.api.model.*;
import banco.vit.vit.api.repository.AgenciaRepository;
import banco.vit.vit.api.repository.BancoRepository;
import banco.vit.vit.api.repository.ContaRepository;
import banco.vit.vit.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public String cadastrarConta(DadosCadastroContaDto dados) {

        Banco banco = bancoRepository.getReferenceById(dados.idBanco());
        Agencia agencia = agenciaRepository.getReferenceById(dados.idAgencia());
        Usuario usuario = usuarioRepository.getReferenceById(dados.IdUsuario());

        Conta conta = new Conta(dados);
        conta.setBanco(banco);
        conta.setAgencia(agencia);
        conta.setUsario(usuario);

        if (conta.getTipoConta() == TipoDeConta.ContaNormal) {

            conta.setCartaoDeCredito(false);
            conta.setLis(false);
            if (!conta.isCartaoDeCredito() && !conta.isLis()) {
                conta.setLimiteCredito(0);
                conta.setLimiteLis(0);
                contaRepository.save(conta);
            }
            if (conta.getLimiteCredito() > 0) {
                throw new RuntimeException("Sua conta é do tipo normal, então não pode haver limite de credito");
            }


        } else if (conta.getTipoConta() == TipoDeConta.ContaEspecial) {
            conta.setCartaoDeCredito(true);
            conta.setLis(false);
            if (conta.getLimiteCredito() == 0)
                throw new DadosErro("Sua conta possui cartão de crédito, informe o valor para: limite crédito");

            contaRepository.save(conta);

        } else if (conta.getTipoConta() == TipoDeConta.ContaPremium) {
            conta.setCartaoDeCredito(true);
            conta.setLis(true);

            if (!(conta.getLimiteCredito() == 0 && !(conta.getLimiteLis() == 0)))
                throw new DadosErro("Sua conta possui cartão de crédito e lis, informe o valor para: limite crédito. Limite Lis");

            contaRepository.save(conta);
        }








        return "Conta cadastrada com sucesso";
    }


//            if(conta.getTipoConta() == TipoDeConta.ContaNormal){
//                System.out.println("entrou aquiiiii");
//                conta.setCartaoDeCredito(true);
//                contaRepository.save(conta);
//
//            }
//            else {
//                System.out.println("entrou naoooooo");
//                throw new DadosErro("você esta passando um tipo de conta especial, verfique se adicinou o valor do lis e do cartão de credito");
//            }
//        RespostaRequisicao dc = new RespostaRequisicao("Conta cadastrada", HttpStatus.OK.toString());
//        return ResponseEntity.ok(dc);

//        }
//        catch (DadosErro erro){
//            RespostaRequisicao rc = new RespostaRequisicao("Tipo de conta premium, informe o valor do saldo de credito" + erro.getMessage(), HttpStatus.BAD_REQUEST.toString());
//
//            return new ResponseEntity<>(rc, HttpStatus.BAD_REQUEST);
//        }
//        catch(Exception ex) {
//            System.out.println("Erro ao salvar o agendamento" + ex.getMessage());
//        }

    //   RespostaRequisicao dc = new RespostaRequisicao("Conta cadastrada", HttpStatus.OK.toString());

//     return new ResponseEntity<>(dc, HttpStatus.OK);


    public Page<DadosListagemContaDto> listarConta(Pageable pagina) {
        return contaRepository.findAllByAtivoTrue(pagina).map(DadosListagemContaDto::new);
    }

    public void atualizarConta(DadosAtualizacaoContaDto dadosAtualizar) {
        Conta conta = contaRepository.findById(dadosAtualizar.id()).get();

        if (conta.getTipoConta() != null) {
            conta.setTipoConta(dadosAtualizar.tipoDeConta());
        }
        if (conta.getSaldo() != 0) {
            conta.setSaldo(dadosAtualizar.saldo());
        }
    }

    public void excluirConta(Long id) {
        Conta conta = contaRepository.getReferenceById(id);
        conta.excluir();
    }
}
