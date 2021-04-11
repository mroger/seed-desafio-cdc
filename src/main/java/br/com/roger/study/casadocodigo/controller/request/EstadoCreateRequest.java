package br.com.roger.study.casadocodigo.controller.request;

import br.com.roger.study.casadocodigo.controller.validator.ExistsId;
import br.com.roger.study.casadocodigo.controller.validator.Unique;
import br.com.roger.study.casadocodigo.model.Estado;
import br.com.roger.study.casadocodigo.model.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Carga: 2
 */

public class EstadoCreateRequest {

    @NotBlank(message = "cdc.estado.nome.obrigatorio")
    @Unique(clazz = Estado.class, field = "nome")
    private String nome;

    @NotNull(message = "cdc.estado.idpais.obrigatorio")
    @ExistsId(clazz = Pais.class)
    private Long idPais;

    //1
    public Estado toModel(EntityManager em) {
        //1
        Pais pais = em.find(Pais.class, idPais);
        Assert.notNull(pais, "O Pais não foi encontrado no banco");

        return new Estado(nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
