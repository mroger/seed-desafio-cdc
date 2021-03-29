package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Carga intrinseca: 1
 */
public class AutorRequest {

    @NotEmpty(message = "cdc.request.nome.obrigatorio")
    private String nome;
    @NotEmpty(message = "cdc.request.email.obrigatorio")
    @Email(message = "cdc.request.email.invalido")
    private String email;
    @NotEmpty(message = "cdc.request.descricao.obrigatorio")
    @Length(max = 400, message = "cdc.request.descricao.maximo")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }
}
