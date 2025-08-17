package br.com.rceen.forumHub.Service;

import br.com.rceen.forumHub.Domain.usuario.DadosDetalhamentoUsuario;
import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioAtualizacao;
import br.com.rceen.forumHub.Domain.usuario.DadosUsuarioCadastro;
import br.com.rceen.forumHub.Domain.usuario.Usuario;
import br.com.rceen.forumHub.Domain.usuario.validacoes.atualizar.ValidadorAtualizacaoUsuario;
import br.com.rceen.forumHub.Domain.usuario.validacoes.cadastrar.ValidadorCadastroUsuario;
import br.com.rceen.forumHub.Domain.usuario.validacoes.desativar.ValidadorDesativacaoUsuario;
import br.com.rceen.forumHub.Repository.UsuarioRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorCadastroUsuario> validadorCadastroUsuarios;

    @Autowired
    private List<ValidadorAtualizacaoUsuario> validadorAtualizacaoUsuario;

    @Autowired
    private List<ValidadorDesativacaoUsuario> validadorDesativacaoUsuarios;

    public Page listar(Pageable paginacao, Boolean ativo) {
        Page page = null;
        if (ativo){
            page = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoUsuario::new);
        } else {
            page  = usuarioRepository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        }
        return page;
    }

    public DadosDetalhamentoUsuario cadastrar(DadosUsuarioCadastro dados) {
        validadorCadastroUsuarios.forEach(validador -> validador.validar(dados));

        var usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public void desativar(Long id) {
        if (!usuarioRepository.existsById(id)) throw new ValidacaoException("Nenhum usuário encontrado com o ID fornecido!");
        validadorDesativacaoUsuarios.forEach(validador -> validador.validar(id));
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.desativar();
    }

    public DadosDetalhamentoUsuario atualizar(Long id, DadosUsuarioAtualizacao dados) {
        if (!usuarioRepository.existsById(id)) throw new ValidacaoException("Nenhum usuário encontrado com o ID fornecido!");
        validadorAtualizacaoUsuario.forEach(validador -> validador.validar(dados));
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizar(dados);
        return new DadosDetalhamentoUsuario(usuario);
    }
}