package com.store.pharmacy.client.controller;

import com.store.pharmacy.client.model.ClientInput;
import com.store.pharmacy.client.model.ClientOutput;
import com.store.pharmacy.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public HttpEntity<ClientOutput> createClient(@Valid @RequestBody ClientInput clientInput) {
        ClientOutput outParam = clientService.create(clientInput);
        return new ResponseEntity<>(outParam, HttpStatus.CREATED);
    }

    @PutMapping("/{idClient}")
    public HttpEntity<ClientOutput> updateClient(@PathVariable("idClient") String idClient, @Valid @RequestBody ClientInput clientInput) {
        ClientOutput outParam = clientService.update(idClient, clientInput);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }
}
