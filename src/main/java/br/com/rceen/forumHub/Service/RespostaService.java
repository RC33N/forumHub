package br.com.rceen.forumHub.Service;
import br.com.rceen.forumHub.Domain.resposta.DadosDetalhamentoResposta;
import br.com.rceen.forumHub.Domain.resposta.DadosRespostaAtualizacao;
import br.com.rceen.forumHub.Domain.resposta.DadosRespostaPostagem;
import br.com.rceen.forumHub.Domain.resposta.Resposta;
import br.com.rceen.forumHub.Domain.resposta.validacoes.atualizacao.ValidadorAtualizacaoResposta;
import br.com.rceen.forumHub.Domain.resposta.validacoes.exclusao.ValidadorExclusaoResposta;
import br.com.rceen.forumHub.Domain.resposta.validacoes.postagem.ValidadorPostagemResposta;
import br.com.rceen.forumHub.Repository.RespostaRepository;
import br.com.rceen.forumHub.Repository.TopicoRepository;
import br.com.rceen.forumHub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorPostagemResposta> validadorPostagemResposta;

    @Autowired
    private List<ValidadorAtualizacaoResposta> validadorAtualizacaoRespostas;

    @Autowired
    private List<ValidadorExclusaoResposta> validadorExclusaoRespostas;

    public DadosDetalhamentoResposta postar(Long idTopico, DadosRespostaPostagem dados) {
        if (!topicoRepository.existsById(idTopico)) throw new ValidacaoException("Nenhum tópico encontrado com o ID fornecido!");

        validadorPostagemResposta.forEach(validador -> validador.validar(idTopico, dados));

        var topico = topicoRepository.getReferenceById(idTopico);
        var resposta = new Resposta(topico, dados);

        respostaRepository.save(resposta);

        return new DadosDetalhamentoResposta(resposta);
    }

    public DadosDetalhamentoResposta atualizar(Long idResposta, DadosRespostaAtualizacao dados) {
        if (!respostaRepository.existsById(idResposta)) throw new ValidacaoException("Nenhuma resposta encontrada com o ID fornecido!");

        validadorAtualizacaoRespostas.forEach(validador -> validador.validar(idResposta, dados));
        var resposta = respostaRepository.getReferenceById(idResposta);
        resposta.atualizar(dados);

        return new DadosDetalhamentoResposta(resposta);
    }

    public void excluir(Long idResposta) {
        if (!respostaRepository.existsById(idResposta)) throw new ValidacaoException("Nenhuma resposta encontrada com o ID fornecido!");

        validadorExclusaoRespostas.forEach(validador -> validador.validar(idResposta));

        respostaRepository.deleteById(idResposta);
    }
}