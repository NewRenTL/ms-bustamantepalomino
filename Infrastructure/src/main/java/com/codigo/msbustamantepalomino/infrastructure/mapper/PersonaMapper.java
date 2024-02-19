package com.codigo.msbustamantepalomino.infrastructure.mapper;


import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.infrastructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public PersonaDTO mapToDTO(PersonaEntity entity)
    {
        return modelMapper.map(entity,PersonaDTO.class);
    }

    public PersonaEntity mapToEntity(PersonaDTO personaDTO)
    {
        return modelMapper.map(personaDTO, PersonaEntity.class);
    }
}
