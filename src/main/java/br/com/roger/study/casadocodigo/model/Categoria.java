package br.com.roger.study.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
    @SequenceGenerator(name="categoria_generator", sequenceName = "categoria_sequence")
    private Long id;

    @NotBlank
    private String nome;

    @Deprecated
    public Categoria() { }

    public Categoria(@NotEmpty final String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Categoria{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            '}';
    }
}
