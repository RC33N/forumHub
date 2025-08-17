package br.com.rceen.forumHub.Repository;

import br.com.rceen.forumHub.Domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository <Curso, Long> {
    Boolean existsByNomeIgnoreCase(String nome);
}