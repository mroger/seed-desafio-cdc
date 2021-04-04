package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Livro;

import java.math.BigDecimal;

public class LivroDetalheResponse {

    private Long id;

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private Integer numeroPaginas;

    private String isbn;

    private AutorResponse autor;

    public LivroDetalheResponse(final Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.autor = AutorResponse.fromAttributes(livro.getNomeAutor(), livro.getDescricaoAutor());
    }

    public static LivroDetalheResponse fromModel(final Livro livro) {
        return new LivroDetalheResponse(livro);
    }

    public Long getId() {
        return id;
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
}
