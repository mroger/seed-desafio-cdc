package br.com.roger.study.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class PaisControllerApiTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    public void testCreate(String nome) throws Exception {

        mockMvcPerform.post("/paises", Map.of("nome", nome))
            .andExpect(status().isCreated());

        mockMvcPerform.post("/paises", Map.of("nome", nome))
            .andExpect(status().isBadRequest());
    }
}
