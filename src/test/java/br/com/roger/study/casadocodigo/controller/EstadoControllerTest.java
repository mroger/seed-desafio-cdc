package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class EstadoControllerTest {

    @Autowired
    private EstadoControllerApiTest apiTest;

    @Autowired
    private MockMvcPerform mockMvcPerform;

    @Property(tries = 100)
    @Label("Fluxo de criacao de Estado")
    void test(@ForAll @NotBlank @StringLength(max = 255) @AlphaChars String nome) throws Exception {

        mockMvcPerform.post("/paises", Map.of("nome", "Nome"));

        apiTest.testCreate(nome, 1L);
    }
}
