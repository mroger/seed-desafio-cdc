package br.com.roger.study.casadocodigo.controller;

import br.com.roger.study.casadocodigo.controller.request.PagamentoCreateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid PagamentoCreateRequest request) {

        return ResponseEntity.created(URI.create("/pagamentos/")).build();
    }
}
