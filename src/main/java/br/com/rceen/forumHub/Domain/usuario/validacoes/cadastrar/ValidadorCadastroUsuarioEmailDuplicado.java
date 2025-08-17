package br.com.rceen.forumHub.Domain.usuario.validacoes.cadastrar;

import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioCadastro;
import br.com.rceen.forumHub.Repository.UsuarioRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastroUsuarioEmailDuplicado implements ValidadorCadastroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosUsuarioCadastro dados) {
        if (usuarioRepository.existsByEmail(dados.email().trim())){
            throw new ValidacaoException("Já existe um usuário cadastrado com o email informado!");
        }
    }
}