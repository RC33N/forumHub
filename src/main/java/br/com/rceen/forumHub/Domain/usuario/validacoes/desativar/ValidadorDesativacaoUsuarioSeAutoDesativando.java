package br.com.rceen.forumHub.Domain.usuario.validacoes.desativar;

import br.com.rceen.forumHub.Service.AutenticacaoService;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDesativacaoUsuarioSeAutoDesativando implements ValidadorDesativacaoUsuario {

    @Override
    public void validar(Long id) {
        if (id.equals(AutenticacaoService.getUsuarioLogado().getId())){
            throw new ValidacaoException("Um usuário não pode se autodesativar!");
        }
    }
}