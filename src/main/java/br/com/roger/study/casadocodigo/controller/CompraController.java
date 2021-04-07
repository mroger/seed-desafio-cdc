package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.CompraCreateRequest;
import br.com.roger.study.casadocodigo.model.Compra;
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
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CompraCreateRequest request) {

        final Compra compra = request.toModel(em);

        em.persist(compra);

        return ResponseEntity.created(URI.create("/pagamentos/")).build();
    }
}
