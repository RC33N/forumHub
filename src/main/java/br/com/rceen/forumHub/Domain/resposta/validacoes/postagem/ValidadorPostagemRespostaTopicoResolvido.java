package br.com.rceen.forumHub.Domain.resposta.validacoes.postagem;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaPostagem;
import br.com.rceen.forumHub.Domain.topico.StatusTopico;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPostagemRespostaTopicoResolvido implements ValidadorPostagemResposta {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Long idTopico, DadosRespostaPostagem dadosRespostaPostagem) {
        if (StatusTopico.RESOLVIDO.equals(topicoRepository.getReferenceById(idTopico).getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não podem receber novas respostas!");
        }
    }
}