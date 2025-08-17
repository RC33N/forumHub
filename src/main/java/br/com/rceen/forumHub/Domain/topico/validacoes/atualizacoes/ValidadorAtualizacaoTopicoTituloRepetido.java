package br.com.rceen.forumHub.Domain.topico.validacoes.atualizacoes;

import br.com.rceen.forumHub.Domain.topico.DadosTopicoAtualizacao;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizacaoTopicoTituloRepetido implements ValidadorAtualizacaoTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Long id, DadosTopicoAtualizacao dadosTopicoAtualizacao) {
        if (dadosTopicoAtualizacao.titulo() != null && topicoRepository.existsByTituloIgnoreCase(dadosTopicoAtualizacao.titulo().trim())) {
            throw new ValidacaoException("Já existe um tópico criado com esse título!");
        }
    }
}