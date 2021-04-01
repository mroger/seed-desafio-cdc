package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Categoria;

import javax.validation.constraints.NotEmpty;

public class NovaCategoriaRequest {

    @Unique(clazz = Categoria.class, field = "nome")
    @NotEmpty(message = "cdc.categoria.nome.obrigatorio")
    private String nome;

    public Categoria toModel() {
        return new Categoria(nome);
    }

    public String getNome() {
        return this.nome;
    }
}
