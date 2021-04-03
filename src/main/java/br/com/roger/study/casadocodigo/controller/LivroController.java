package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.NovoLivroRequest;
import br.com.roger.study.casadocodigo.controller.response.LivroIdentificacaoResponse;
import br.com.roger.study.casadocodigo.model.Livro;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = { "/livros" })
public class LivroController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid final NovoLivroRequest request) {

        final Livro novoLivro = request.toModel(em);

        em.persist(novoLivro);

        return ResponseEntity.created(URI.create("/livros/" + novoLivro.getId())).build();
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LivroIdentificacaoResponse>> findAll() {

        final List<LivroIdentificacaoResponse> response = em.createQuery("from Livro", Livro.class)
            .getResultStream()
            .map(livro -> LivroIdentificacaoResponse.fromAttributes(livro.getId(), livro.getTitulo()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
