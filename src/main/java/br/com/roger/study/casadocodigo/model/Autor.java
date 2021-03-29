package br.com.roger.study.casadocodigo.model;

import org.hibernate.validator.constraints.Length;

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
    private String nome;
    private String email;
    private String descricao;
    private final Instant registradoEm;

    public Autor(@NotEmpty final String nome, @NotEmpty @Email final String email,
            @NotEmpty @Length(max = 400) final String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.registradoEm = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Instant getRegistradoEm() {
        return registradoEm;
    }
}
