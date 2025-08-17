package br.com.rceen.forumHub.Domain.topico.validacoes.atualizacoes;

import br.com.rceen.forumHub.Domain.topico.DadosTopicoAtualizacao;

public interface ValidadorAtualizacaoTopico {

    void validar(Long id, DadosTopicoAtualizacao dadosTopicoAtualizacao);

}