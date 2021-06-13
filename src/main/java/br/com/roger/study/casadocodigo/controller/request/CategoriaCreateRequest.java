package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Categoria;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

/**
 * Carga: 1
 */

public class CategoriaCreateRequest {

    @Unique(clazz = Categoria.class, field = "nome")
    @NotBlank(message = "cdc.categoria.nome.obrigatorio")
    private String nome;

    public Categoria toModel() {
        Assert.hasText(nome,"O nome deve ser fornecido");

        return new Categoria(nome);
    }

    public String getNome() {
        return this.nome;
    }
}
