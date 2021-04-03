package br.com.roger.study.casadocodigo.model;

import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.controller.validator.Unique;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_generator")
    @SequenceGenerator(name="livro_generator", sequenceName = "livro_sequence")
    private Long id;

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    private String isbn;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    private Livro(@NotEmpty String titulo, @NotEmpty String resumo, String sumario, @NotNull BigDecimal preco,
            @NotNull @Min(value = 100) Integer numeroPaginas, @NotEmpty @Unique(clazz = Livro.class, field = "isbn") String isbn,
            @NotNull @Future LocalDate dataPublicacao, @NotNull @ExistsId(clazz = Categoria.class) Categoria categoria,
            @NotNull @ExistsId(clazz = Autor.class) Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public static class LivroBuilder {

        private String titulo;
        private String resumo;
        private String sumario;
        private BigDecimal preco;
        private Integer numeroPaginas;
        private String isbn;
        private LocalDate dataPublicacao;
        private Categoria categoria;
        private Autor autor;

        public LivroBuilder() { }

        public LivroBuilder withTitulo(@NotEmpty @Unique(clazz = Livro.class, field = "titulo") String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LivroBuilder withResumo(@NotEmpty @Length(max = 500) String resumo) {
            this.resumo = resumo;
            return this;
        }

        public LivroBuilder withSumario(String sumario) {
            this.sumario = sumario;
            return this;
        }

        public LivroBuilder withPreco(@NotNull @Min(value = 20) BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public LivroBuilder withNumeroPaginas(@NotNull @Min(value = 100) Integer numeroPaginas) {
            this.numeroPaginas = numeroPaginas;
            return this;
        }

        public LivroBuilder withIsbn(@NotEmpty @Unique(clazz = Livro.class, field = "isbn") String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LivroBuilder withDataPublicacao(@NotNull @Future LocalDate dataPublicacao) {
            this.dataPublicacao = dataPublicacao;
            return this;
        }

        public LivroBuilder withCategoria(@ExistsId(clazz = Categoria.class) @NotNull Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public LivroBuilder withAutor(@ExistsId(clazz = Autor.class) @NotNull Autor autor) {
            this.autor = autor;
            return this;
        }

        public Livro build() {
            return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
        }
    }
}
