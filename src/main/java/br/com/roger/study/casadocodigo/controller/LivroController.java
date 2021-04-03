package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.NovoLivroRequest;
import br.com.roger.study.casadocodigo.model.Livro;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

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
}
