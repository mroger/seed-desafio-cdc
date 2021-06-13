package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
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
class CategoriaControllerTest {

    @Autowired
    private CategoriaControllerApiTest apiTest;

    private final Set<String> nomesUnique = new HashSet<>();

    @Property(tries = 100)
    @Label("Fluxo de teste de nova categoria")
    void testApi(@ForAll @NotBlank @StringLength(min = 1, max = 255) String nome) throws Exception {

        Assumptions.assumeTrue(nomesUnique.add(nome), "nome repetido");

        apiTest.test(nome);
    }
}
