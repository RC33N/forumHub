package br.com.rceen.forumHub.Domain.usuario.validacoes.atualizar;


import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioAtualizacao;
import br.com.rceen.forumHub.Repository.UsuarioRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizacaoUsuarioEmailDuplicado implements ValidadorAtualizacaoUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosUsuarioAtualizacao dados) {
        if (dados.email() != null && usuarioRepository.existsByEmail(dados.email().trim())){
            throw new ValidacaoException("Já existe um usuário cadastrado com o email informado!");
        }
    }

}