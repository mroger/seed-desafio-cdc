package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.web.api.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerTest {

    @Autowired
    private AutorControllerApiTest apiTest;

    private final Set<String> emailsUnique = new HashSet<>();

    @Property(tries = 10)
    void testApi(
        @ForAll @NotBlank @StringLength(min = 1, max = 255) String name,
        @ForAll @Email @NotBlank @StringLength(min = 1, max = 255) String email, // customizar em um método que gera emails únicos
        @ForAll @NotBlank @StringLength(min = 1, max = 400) String description) throws Exception {

            assumeTrue(emailsUnique.add(email));

            apiTest.test(name, email, description);
    }
}
