package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.PaisCreateRequest;
import br.com.roger.study.casadocodigo.model.Pais;
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
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid PaisCreateRequest request) {

        Pais novoPais = request.toModel();

        em.persist(novoPais);

        return ResponseEntity.created(URI.create("/paises/" + novoPais.getId())).build();
    }
}
