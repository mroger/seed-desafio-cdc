package br.com.roger.study.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EstadoControllerApiTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    public void testCreate(String nome, Long idPais) throws Exception {

        mockMvcPerform.post("/estados", Map.of("nome", nome, "idPais", idPais.toString()));
    }
}
