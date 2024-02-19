package com.codigo.msbustamantepalomino.infrastructure.repository;

import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.infrastructure.entity.PersonaEntity;
import com.codigo.msbustamantepalomino.infrastructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {
    List<PersonaEntity> findAllByEstado(Integer estado);
    PersonaEntity findByNumDocu(String numDocu);
}
