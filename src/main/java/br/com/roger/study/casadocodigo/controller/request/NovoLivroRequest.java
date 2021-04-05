package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Autor;
import br.com.roger.study.casadocodigo.model.Categoria;
import br.com.roger.study.casadocodigo.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank(message = "cdc.livro.titulo.obrigatorio")
    @Unique(clazz = Livro.class, field = "titulo") //O atributo message poderia se passado aqui e sobrescrito o default
    private String titulo;

    @NotBlank(message = "cdc.livro.resumo.obrigatorio")
    @Length(max = 500, message = "cdc.livro.resumo.maximo")
    private String resumo;

    private String sumario;

    @NotNull(message = "cdc.livro.preco.obrigatorio")
    @Min(value = 20, message = "cdc.livro.preco.minimo")
    private BigDecimal preco;

    @NotNull(message = "cdc.livro.numeropaginas.obrigatorio")
    @Min(value = 100, message = "cdc.livro.numeropaginas.minimo")
    private Integer numeroPaginas;

    @NotBlank(message = "cdc.livro.isbn.obrigatorio")
    @Unique(clazz = Livro.class, field = "isbn")
    private String isbn;

    @NotNull(message = "cdc.livro.datapublicacao.obrigatorio")
    @Future(message = "cdc.livro.datapublicacao.futuro")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull(message = "cdc.livro.idcategoria.obrigatorio")
    @ExistsId(clazz = Categoria.class)
    private Long idCategoria;

    @NotNull(message = "cdc.livro.idautor.obrigatorio")
    @ExistsId(clazz = Autor.class)
    private Long idAutor;

    /*
     * Tudo bem nao colocar o atributo message no construtor. Vai ser usado o anotado no atributo.
     * Podemos apenas deixar a dica
     */
    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Length(max = 500) String resumo, String sumario,
            @NotNull @Min(value = 20) BigDecimal preco, @NotNull @Min(value = 100) Integer numeroPaginas, @NotBlank String isbn,
            @NotNull @Future LocalDate dataPublicacao, @NotNull @ExistsId(clazz = Categoria.class) Long idCategoria, @NotNull @ExistsId(clazz = Autor.class) Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toModel(EntityManager em) {
        @NotNull Autor autor = em.find(Autor.class, idAutor);
        Assert.state(autor != null, "Não foi encontrado autor para associar ao Livro: " + idAutor);

        @NotNull Categoria categoria = em.find(Categoria.class, idCategoria);
        Assert.state(categoria != null, "Não foi encontrada categoria para associar ao Livro: " + idCategoria);

        return new Livro.LivroBuilder()
            .withTitulo(titulo)
            .withResumo(resumo)
            .withSumario(sumario)
            .withPreco(preco)
            .withNumeroPaginas(numeroPaginas)
            .withIsbn(isbn)
            .withDataPublicacao(dataPublicacao)
            .withCategoria(categoria)
            .withAutor(autor)
            .build();
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }
}
