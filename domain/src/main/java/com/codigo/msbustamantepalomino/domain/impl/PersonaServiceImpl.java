package com.codigo.msbustamantepalomino.domain.impl;

import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.domain.aggregates.requests.RequestPersona;
import com.codigo.msbustamantepalomino.domain.ports.in.PersonServiceIn;
import com.codigo.msbustamantepalomino.domain.ports.out.PersonServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonServiceIn {

    private final PersonServiceOut personServiceOut;
    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return personServiceOut.crearPersonOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return personServiceOut.obtenerPersonaOut(id);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personServiceOut.obtenerTodosOut();
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonDocuIn(String numDocu) {
        return personServiceOut.obtenerPersonDocuOut(numDocu);
    }

    @Override
    public List<PersonaDTO> obtenerTodosEstadoIn() {
        return personServiceOut.obtenerTodosEstadoOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personServiceOut.actualizarOut(id,requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personServiceOut.deleteOut(id);
    }

}
