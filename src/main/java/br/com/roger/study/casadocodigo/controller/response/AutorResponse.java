package br.com.roger.study.casadocodigo.controller.response;

import java.time.Instant;

public class AutorResponse {

    private Long id;
    private String nome;
    private String email;
    private String descricao;
    private Instant registradoEm;

    public AutorResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public static AutorResponse fromAttributes(final String nome, final String descricao) {
        return new AutorResponse(nome, descricao);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Instant getRegistradoEm() {
        return registradoEm;
    }
}
