package br.com.roger.study.casadocodigo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AutorControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    public void test(String nome, String email, String descricao) throws Exception {

        String payload = new JSONObject()
            .put("nome", nome)
            .put("email", email)
            .put("descricao", descricao)
            .toString();

        mockMvc.perform(post("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isCreated());

        mockMvc.perform(post("/autores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isBadRequest());
    }
}
