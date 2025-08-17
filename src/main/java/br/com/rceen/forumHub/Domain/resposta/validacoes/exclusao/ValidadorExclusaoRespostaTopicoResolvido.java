package br.com.rceen.forumHub.Domain.resposta.validacoes.exclusao;

import br.com.rceen.forumHub.Domain.topico.StatusTopico;
import br.com.rceen.forumHub.Repository.RespostaRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorExclusaoRespostaTopicoResolvido implements ValidadorExclusaoResposta {

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(Long idResposta) {
        if (StatusTopico.RESOLVIDO.equals(respostaRepository.getReferenceById(idResposta).getTopico().getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não permitem exlusão de respostas!");
        }
    }
}