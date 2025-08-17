package br.com.rceen.forumHub.Domain.resposta.validacoes.postagem;

import br.com.rceen.forumHub.Domain.resposta.DadosRespostaPostagem;

public interface ValidadorPostagemResposta {
    void validar(Long idTopico, DadosRespostaPostagem dadosRespostaPostagem);
}
