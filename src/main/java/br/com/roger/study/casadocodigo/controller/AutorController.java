package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.AutorCreateRequest;
import br.com.roger.study.casadocodigo.model.Autor;
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

/**
 * Carga intrinseca: 2
 */
@RestController
@RequestMapping(value = { "/autores" })
public class AutorController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid final AutorCreateRequest request) {

        Autor novoAutor = request.toModel();

        em.persist(novoAutor);

        return ResponseEntity.created(URI.create("/autores/" + novoAutor.getId())).build();
    }
}
