package br.com.roger.study.casadocodigo.model;

import br.com.roger.study.casadocodigo.controller.validator.Unique;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_generator")
    @SequenceGenerator(name="pais_generator", sequenceName = "pais_sequence")
    private Long id;

    @NotBlank
    private String nome;

    @Deprecated
    public Pais() { }

    public Pais(@NotBlank @Unique(clazz = Pais.class, field = "nome") String nome) {
        Assert.hasLength(nome, "O nome do País é obrigatório");
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
