package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.CupomDescontoCreateRequest;
import br.com.roger.study.casadocodigo.model.Cupom;
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
@RequestMapping("/cupons")
public class CupomController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CupomDescontoCreateRequest request) {

        Cupom novoCupom = request.toModel();

        em.persist(novoCupom);

        return ResponseEntity.created(URI.create("/cupons/" + novoCupom.getId())).build();
    }
}