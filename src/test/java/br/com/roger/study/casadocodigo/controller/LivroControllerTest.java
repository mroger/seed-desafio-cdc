package br.com.roger.study.casadocodigo.controller;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.time.api.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static br.com.roger.study.casadocodigo.controller.MockMvcPerform.string;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@JqwikSpringSupport
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class LivroControllerTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    @Autowired
    private LivroControllerApiTest apiTest;

    private Set<String> uniqueTitulos = new HashSet<>();

    private Set<String> uniqueIsbn = new HashSet<>();

    @Property(tries = 10)
    @Label("Fluxo de criacao de um livro")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void test(
            @ForAll @NotBlank @StringLength(min = 1, max = 255) String titulo,
            @ForAll @NotBlank @StringLength(min = 1, max = 500) String resumo,
            @ForAll @StringLength(min = 1, max = 255) String sumario,
            @ForAll @BigRange(min = "20", max = "11602713925812700", minIncluded = true) BigDecimal preco,
            @ForAll @IntRange(min = 100) Integer numeroPaginas,
            @ForAll @NotBlank @StringLength(min = 1, max = 255) @NumericChars String isbn,
            @ForAll("futureDate") LocalDate dataPublicacao) throws Exception {

        assumeTrue(uniqueTitulos.add(titulo));
        assumeTrue(uniqueIsbn.add(isbn));

        mockMvcPerform.post("/categorias", Map.of("nome", "Nome"));
        mockMvcPerform.post("/autores", Map.of("nome", "Nome", "email", "some@email.com", "descricao", "Descricao"));

        apiTest.testCreate(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, 1L, 1L);
    }

    @Property(tries = 30)
    @Label("Fluxo de detalhes de um livro")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void test2(
            @ForAll @NotBlank @StringLength(min = 1, max = 255) String titulo,
            @ForAll @NotBlank @StringLength(min = 1, max = 500) String resumo,
            @ForAll @StringLength(min = 1, max = 255) String sumario,
            @ForAll @BigRange(min = "20", max = "11602713925812700", minIncluded = true) BigDecimal preco,
            @ForAll @IntRange(min = 100) Integer numeroPaginas,
            @ForAll @NotBlank @StringLength(min = 1, max = 255) @NumericChars String isbn,
            @ForAll("futureDate") LocalDate dataPublicacao) throws Exception {

        assumeTrue(uniqueTitulos.add(titulo));
        assumeTrue(uniqueIsbn.add(isbn));

        //String str = new ObjectMapper().writeValueAsString(preco);

        mockMvcPerform.post("/categorias", Map.of("nome", "Nome"));
        mockMvcPerform.post("/autores", Map.of("nome", "Nome", "email", "some@email.com", "descricao", "Descricao"));
        mockMvcPerform.post("/livros", Map.of("titulo", titulo, "resumo", resumo, "sumario", sumario,
            "preco", string(preco), "numeroPaginas", string(numeroPaginas), "isbn", isbn, "dataPublicacao",
            string(dataPublicacao), "idCategoria", "1", "idAutor", "1"));

        apiTest.testDetail(1L, titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, "Nome", "Descricao");
    }

    @Provide
    Arbitrary<LocalDate> futureDate() {
        return Dates.dates().atTheEarliest(LocalDate.now().plusDays(1L));
    }
}
