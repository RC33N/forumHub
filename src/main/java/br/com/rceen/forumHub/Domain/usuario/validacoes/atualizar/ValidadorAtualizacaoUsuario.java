package br.com.rceen.forumHub.Domain.usuario.validacoes.atualizar;

import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioAtualizacao;

public interface ValidadorAtualizacaoUsuario {
    void validar(DadosUsuarioAtualizacao dados);
}
