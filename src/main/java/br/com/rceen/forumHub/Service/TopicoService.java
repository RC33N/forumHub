package br.com.rceen.forumHub.Service;

import br.com.rceen.forumHub.Domain.curso.Curso;
import br.com.rceen.forumHub.Domain.topico.DadosDetalhamentoTopico;
import br.com.rceen.forumHub.Domain.topico.DadosTopicoAtualizacao;
import br.com.rceen.forumHub.Domain.topico.DadosTopicoPostagem;
import br.com.rceen.forumHub.Domain.topico.Topico;
import br.com.rceen.forumHub.Domain.topico.validacoes.atualizacoes.ValidadorAtualizacaoTopico;
import br.com.rceen.forumHub.Domain.topico.validacoes.postagem.ValidadorPostagemTopico;
import br.com.rceen.forumHub.Repository.CursoRepository;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorPostagemTopico> validadorPostagemTopicos;

    @Autowired
    private List<ValidadorAtualizacaoTopico> validadorAtualizacaoTopicos;

    public DadosDetalhamentoTopico postar(@Valid DadosTopicoPostagem dados) {
        if (!cursoRepository.existsById(dados.idCurso())) throw new ValidacaoException("Nenhum curso cadastrado com o ID informado!");

        validadorPostagemTopicos.forEach(validador -> validador.validar(dados));

        var curso = cursoRepository.findById(dados.idCurso()).get();
        var usuario = AutenticacaoService.getUsuarioLogado();
        var topico = new Topico(dados, usuario, curso);
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico atualizar(Long id, DadosTopicoAtualizacao dados) {
        if (!topicoRepository.existsById(id)) throw new ValidacaoException("Nenhum tópico encontrado com o ID informado!");

        Curso curso = null;
        if (dados.idCurso() != null){
            if (!cursoRepository.existsById(dados.idCurso())){
                throw new ValidacaoException("Nenhum curso cadastrado com o ID informado!");
            } else {
                curso = cursoRepository.getReferenceById(dados.idCurso());
            }
        }

        validadorAtualizacaoTopicos.forEach(validador -> validador.validar(id, dados));

        var topico = topicoRepository.getReferenceById(id);
        topico.atualizarInformacoes(dados, curso);

        return new DadosDetalhamentoTopico(topico);
    }
}