package com.codigo.msbustamantepalomino.application.controller;


import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.domain.aggregates.requests.RequestPersona;
import com.codigo.msbustamantepalomino.domain.ports.in.PersonServiceIn;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonServiceIn personServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDTO> register(@RequestBody RequestPersona requestPersona)
    {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                    body(personServiceIn.
                            crearPersonaIn(requestPersona));
    }

    @GetMapping("/{dni}")
    public ResponseEntity<PersonaDTO> getPerson(@PathVariable String dni)
    {
        return ResponseEntity.
                status(HttpStatus.OK).
                    body(personServiceIn.obtenerPersonDocuIn(dni).get());
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllEstateTrue()
    {
        return ResponseEntity.
                status(HttpStatus.OK).
                    body(personServiceIn.obtenerTodosEstadoIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> update(@PathVariable Long id,@RequestBody RequestPersona requestPersona)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(personServiceIn.actualizarIn(id,requestPersona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO> delete(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personServiceIn.deleteIn(id));
    }








}
