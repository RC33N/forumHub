package br.com.rceen.forumHub.Domain.resposta.validacoes.atualizacao;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaAtualizacao;

public interface ValidadorAtualizacaoResposta {
    void validar(Long idResposta, DadosRespostaAtualizacao dadosRespostaAtualizacao);
}
