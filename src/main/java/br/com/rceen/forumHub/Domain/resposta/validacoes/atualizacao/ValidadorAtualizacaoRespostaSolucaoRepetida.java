package br.com.rceen.forumHub.Domain.resposta.validacoes.atualizacao;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaAtualizacao;
import br.com.rceen.forumHub.Repository.RespostaRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizacaoRespostaSolucaoRepetida implements ValidadorAtualizacaoResposta {

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(Long idResposta, DadosRespostaAtualizacao dadosRespostaAtualizacao) {
        var resposta = respostaRepository.getReferenceById(idResposta);
        if (dadosRespostaAtualizacao.solucao() != null && respostaRepository.optionalRespostaByIdTopicoAndSolucaoIgnoreCase(resposta.getTopico().getId(), dadosRespostaAtualizacao.solucao().trim()).isPresent()){
            throw new ValidacaoException("Já existe uma resposta para este tópico com essa solução!");
        }
    }
}