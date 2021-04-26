package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Pais;
import org.springframework.beans.BeanUtils;

public class PaisResponse {

    private Long id;

    private String nome;

    public static PaisResponse fromModel(Pais pais) {
        PaisResponse paisResponse = new PaisResponse();
        BeanUtils.copyProperties(pais, paisResponse, "estados");
        return paisResponse;
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
}
