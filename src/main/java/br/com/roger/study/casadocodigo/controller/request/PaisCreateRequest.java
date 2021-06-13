package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Pais;

import javax.validation.constraints.NotBlank;

/**
 * Carga: 1
 */

public class PaisCreateRequest {

    @NotBlank(message = "cdc.pais.nome.obrigatorio")
    @Unique(clazz = Pais.class, field = "nome")
    private String nome;

    public Pais toModel() {
        //Checar pre-condicao

        return new Pais(nome);
    }

    public String getNome() {
        return nome;
    }
}
