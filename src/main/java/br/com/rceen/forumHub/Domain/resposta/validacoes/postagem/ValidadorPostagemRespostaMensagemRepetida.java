package br.com.rceen.forumHub.Domain.resposta.validacoes.postagem;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaPostagem;
import br.com.rceen.forumHub.Repository.RespostaRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPostagemRespostaMensagemRepetida implements ValidadorPostagemResposta {

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(Long idTopico, DadosRespostaPostagem dadosRespostaPostagem) {
        if (respostaRepository.optionalRespostaByIdTopicoAndMensagemIgnoreCase(idTopico, dadosRespostaPostagem.mensagem().trim()).isPresent()){
            throw new ValidacaoException("Já existe uma resposta para este tópico com essa mensagem!");
        }
    }
}