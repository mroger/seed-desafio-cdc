package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class PaisControllerTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    @Autowired
    private PaisControllerApiTest apiTest;

    private Set<String> paisesCriados = new HashSet<>();

    @Property(tries = 100)
    @Label("Fluxo criacao pais")
    void test1(@ForAll @StringLength(max = 255) @NotBlank @AlphaChars String nome) throws Exception {

        Assumptions.assumeTrue(paisesCriados.add(nome));

        apiTest.testCreate(nome);
    }
}
