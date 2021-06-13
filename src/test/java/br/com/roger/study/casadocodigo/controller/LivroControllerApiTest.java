package br.com.roger.study.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static br.com.roger.study.casadocodigo.controller.MockMvcPerform.string;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class LivroControllerApiTest {

    @Autowired
    private MockMvcPerform mockMvcPerform;

    public void testCreate(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroPaginas, String isbn,
            LocalDate dataPublicacao, Long idCategoria, Long idAutor) throws Exception {

        mockMvcPerform.post("/livros", Map.of("titulo", titulo, "resumo", resumo, "sumario", sumario,
                "preco", string(preco), "numeroPaginas", string(numeroPaginas), "isbn", isbn, "dataPublicacao",
                string(dataPublicacao), "idCategoria", string(idCategoria), "idAutor", string(idAutor)))
            .andExpect(status().isCreated());

        mockMvcPerform.post("/livros", Map.of("titulo", titulo, "resumo", resumo, "sumario", sumario,
            "preco", string(preco), "numeroPaginas", string(numeroPaginas), "isbn", isbn, "dataPublicacao",
            string(dataPublicacao), "idCategoria", string(idCategoria), "idAutor", string(idAutor)))
            .andExpect(status().isBadRequest());
    }

    public void testDetail(long idLivro, String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroPaginas,
            String isbn, LocalDate dataPublicacao, String nomeAutor, String descricaoAutor) throws Exception {

        mockMvcPerform.get("/livros/{id}", idLivro)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", is(titulo)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.resumo", is(resumo)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.sumario", is(sumario)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.preco", is(preco), BigDecimal.class))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numeroPaginas", is(numeroPaginas)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", is(isbn)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.dataPublicacao", is(string(dataPublicacao))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.autor.nome", is(nomeAutor)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.autor.descricao", is(descricaoAutor)));
    }
}

