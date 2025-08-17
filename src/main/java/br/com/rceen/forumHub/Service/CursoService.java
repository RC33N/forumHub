package br.com.rceen.forumHub.Service;

import br.com.rceen.forumHub.Domain.curso.Curso;
import br.com.rceen.forumHub.Domain.curso.DadosCursoCadastro;
import br.com.rceen.forumHub.Domain.curso.DadosDetalhamentoCurso;
import br.com.rceen.forumHub.Repository.CursoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DadosDetalhamentoCurso cadastrar(DadosCursoCadastro dados) {
        if (cursoRepository.existsByNomeIgnoreCase(dados.nome().trim())) throw new ValidacaoException
                ("Já existe um curso cadastrado com o nome informado!");
        var curso = new Curso(dados);
        cursoRepository.save(curso);

        return new DadosDetalhamentoCurso(curso);
    }
}