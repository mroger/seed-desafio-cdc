package br.com.roger.study.casadocodigo.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

/**
 * Carga intrinseca: 0
 */
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_generator")
    @SequenceGenerator(name="autor_generator", sequenceName = "autor_sequence")
    private Long id;
    @NotEmpty
    private String nome;
    @NotEmpty @Email
    private String email;
    @NotEmpty @Length(max = 400)
    private String descricao;
    private final Instant registradoEm;

    public Autor(@NotEmpty final String nome, @NotEmpty @Email final String email,
            @NotEmpty @Length(max = 400) final String descricao) {
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
