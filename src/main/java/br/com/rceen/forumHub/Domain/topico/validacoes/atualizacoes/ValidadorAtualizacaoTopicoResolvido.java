package br.com.rceen.forumHub.Domain.topico.validacoes.atualizacoes;

import br.com.rceen.forumHub.Domain.topico.DadosTopicoAtualizacao;
import br.com.rceen.forumHub.Domain.topico.StatusTopico;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizacaoTopicoResolvido implements ValidadorAtualizacaoTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Long id, DadosTopicoAtualizacao dadosTopicoAtualizacao) {
        if (StatusTopico.RESOLVIDO.equals(topicoRepository.getReferenceById(id).getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não podem ser atualizados!");
        }
    }
}