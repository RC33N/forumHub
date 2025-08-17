package br.com.rceen.forumHub.Domain.resposta.validacoes.atualizacao;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaAtualizacao;
import br.com.rceen.forumHub.Domain.topico.StatusTopico;
import br.com.rceen.forumHub.Repository.RespostaRepository;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizacaoRespostaTopicoResolvido implements ValidadorAtualizacaoResposta {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(Long idResposta, DadosRespostaAtualizacao dadosRespostaAtualizacao) {
        var resposta = respostaRepository.getReferenceById(idResposta);
        if (StatusTopico.RESOLVIDO.equals(topicoRepository.getReferenceById(resposta.getTopico().getId()).getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não permitem edição de suas respostas!");
        }
    }
}