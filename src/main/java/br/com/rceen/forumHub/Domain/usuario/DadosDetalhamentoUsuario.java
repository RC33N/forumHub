package br.com.rceen.forumHub.Domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, Boolean ativo) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getAtivo());
    }
}