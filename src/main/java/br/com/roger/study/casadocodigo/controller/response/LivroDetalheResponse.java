package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDetalheResponse {

    private final String titulo;

    private final String resumo;

    private final String sumario;

    private final BigDecimal preco;

    private final Integer numeroPaginas;

    private final String isbn;

    private final LocalDate dataPublicacao;

    private final AutorResponse autor;

    public LivroDetalheResponse(final Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.autor = new AutorResponse(livro.getNomeAutor(), livro.getDescricaoAutor());
    }

    public static LivroDetalheResponse fromModel(final Livro livro) {
        return new LivroDetalheResponse(livro);
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    static class AutorResponse {

        private final String nome;
        private final String descricao;

        public AutorResponse(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}