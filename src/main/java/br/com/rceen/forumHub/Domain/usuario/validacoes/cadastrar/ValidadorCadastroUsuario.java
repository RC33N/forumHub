package br.com.rceen.forumHub.Domain.usuario.validacoes.cadastrar;

import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioCadastro;

public interface ValidadorCadastroUsuario {
    void validar(DadosUsuarioCadastro dados);
}
