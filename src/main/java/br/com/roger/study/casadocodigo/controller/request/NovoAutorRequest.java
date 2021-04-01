package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Carga intrinseca: 1
 */
public class NovoAutorRequest {

    @NotEmpty(message = "cdc.request.nome.obrigatorio")
    private String nome;

    @NotEmpty(message = "cdc.request.email.obrigatorio")
    @Email(message = "cdc.request.email.invalido")
    @Unique(clazz = Autor.class, field = "email")
    private String email;

    @NotEmpty(message = "cdc.request.descricao.obrigatorio")
    @Length(max = 400, message = "cdc.request.descricao.maximo")
    private String descricao;

    public NovoAutorRequest(
            @NotEmpty(message = "cdc.request.nome.obrigatorio") String nome,
            @NotEmpty(message = "cdc.request.email.obrigatorio") @Email(message = "cdc.request.email.invalido") String email,
            @NotEmpty(message = "cdc.request.descricao.obrigatorio") @Length(max = 400, message = "cdc.request.descricao.maximo") String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
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
}
