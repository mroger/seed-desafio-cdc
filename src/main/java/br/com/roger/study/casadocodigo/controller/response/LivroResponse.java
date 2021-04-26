package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Livro;

public class LivroResponse {

    private Long id;

    private String titulo;

    public static LivroResponse fromModel(Livro livro) {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setId(livro.getId());
        livroResponse.setTitulo(livro.getTitulo());
        return livroResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
