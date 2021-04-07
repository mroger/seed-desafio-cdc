package br.com.roger.study.casadocodigo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Carga intrinseca: 1
 */
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_generator")
    @SequenceGenerator(name="autor_generator", sequenceName = "autor_sequence")
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank @Length(max = 400)
    private String descricao;
    @NotNull
    private Instant registradoEm;

    @Deprecated
    public Autor() { }

    public Autor(@NotBlank final String nome, @NotBlank @Email final String email,
            @NotBlank @Length(max = 400) final String descricao) {
        validateInput(nome, email, descricao);

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.registradoEm = Instant.now();
    }

    private void validateInput(String nome, String email, String descricao) {
        Assert.hasLength(nome, "O nome do autor é obrigatório");
        Assert.hasLength(email, "O email do autor é obrigatório");
        Assert.hasLength(descricao, "A descricao do autor é obrigatória");
        if (descricao.length() > 400) {
            throw new IllegalArgumentException("A descrição do Autor deve ter no máximo 400 caracteres");
        }
    }

    public Long getId() {
        return this.id;
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

    @Override
    public String toString() {
        return "Autor{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", email='" + email + '\'' +
            ", descricao='" + descricao + '\'' +
            ", registradoEm=" + registradoEm +
            '}';
    }
}
