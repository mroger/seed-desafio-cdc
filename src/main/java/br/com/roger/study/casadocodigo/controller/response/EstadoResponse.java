package br.com.roger.study.casadocodigo.controller.response;

import br.com.roger.study.casadocodigo.model.Estado;
import org.springframework.beans.BeanUtils;

public class EstadoResponse {

    private Long id;

    private String nome;

    public static EstadoResponse fromModel(Estado estado) {
        EstadoResponse estadoResponse = new EstadoResponse();
        BeanUtils.copyProperties(estado, estadoResponse, "pais");
        return estadoResponse;
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
