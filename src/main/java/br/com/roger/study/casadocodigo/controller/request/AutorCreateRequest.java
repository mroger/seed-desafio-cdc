package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Autor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Carga intrinseca: 1
 */
public class AutorCreateRequest {

    @NotBlank(message = "cdc.autor.nome.obrigatorio")
    private String nome;

    @NotBlank(message = "cdc.autor.email.obrigatorio")
    @Email(message = "cdc.autor.email.invalido")
    @Unique(clazz = Autor.class, field = "email")
    private String email;

    @NotBlank(message = "cdc.autor.descricao.obrigatorio")
    @Length(max = 400, message = "cdc.autor.descricao.maximo")
    private String descricao;

    public Autor toModel() {
        Assert.hasText(nome,"O nome deve ser fornecido");
        Assert.hasText(email,"O email deve ser fornecido");
        Assert.hasText(descricao,"A descricao deve ser fornecida");
        Assert.isTrue(descricao.length() <= 400,"A descrição deve ter no máximo 400 caracteres");

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
