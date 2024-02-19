package com.codigo.msbustamantepalomino.domain.ports.out;

import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.domain.aggregates.requests.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonServiceOut {
    PersonaDTO crearPersonOut(RequestPersona requestPersona);

    Optional<PersonaDTO> obtenerPersonaOut(Long id);

    Optional<PersonaDTO> obtenerPersonDocuOut(String numDocu);


    List<PersonaDTO> obtenerTodosOut();

    List<PersonaDTO> obtenerTodosEstadoOut();

    PersonaDTO actualizarOut(Long id, RequestPersona requestPersona);

    PersonaDTO deleteOut(Long id);
}
