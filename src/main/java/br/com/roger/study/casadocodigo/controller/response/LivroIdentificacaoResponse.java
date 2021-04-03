package br.com.roger.study.casadocodigo.controller.response;

public class LivroIdentificacaoResponse {

    private Long id;
    private String titulo;

    public LivroIdentificacaoResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public static LivroIdentificacaoResponse fromAttributes(final Long id, final String titulo) {
        return new LivroIdentificacaoResponse(id, titulo);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
