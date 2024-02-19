package com.codigo.msbustamantepalomino.domain.ports.in;

import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.domain.aggregates.requests.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonServiceIn {
    PersonaDTO crearPersonaIn(RequestPersona requestPersona);

    Optional<PersonaDTO> obtenerPersonaIn(Long id);

    Optional<PersonaDTO> obtenerPersonDocuIn(String numDocu);

    List<PersonaDTO> obtenerTodosIn();

    List<PersonaDTO> obtenerTodosEstadoIn();

    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);

    PersonaDTO deleteIn(Long id);
}
