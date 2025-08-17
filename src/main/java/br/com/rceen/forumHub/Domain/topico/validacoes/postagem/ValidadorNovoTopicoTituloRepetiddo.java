package br.com.rceen.forumHub.Domain.topico.validacoes.postagem;


import br.com.rceen.forumHub.Domain.topico.DadosTopicoPostagem;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNovoTopicoTituloRepetiddo implements ValidadorPostagemTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosTopicoPostagem dadosTopicoPostagem) {
        if (topicoRepository.existsByTituloIgnoreCase(dadosTopicoPostagem.titulo().trim())) throw new ValidacaoException("Já existe um tópico criado com esse título!");
    }
}