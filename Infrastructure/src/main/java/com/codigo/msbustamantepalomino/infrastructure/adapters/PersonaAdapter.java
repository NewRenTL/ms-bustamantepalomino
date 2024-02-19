package com.codigo.msbustamantepalomino.infrastructure.adapters;

import com.codigo.msbustamantepalomino.domain.aggregates.constants.Constants;
import com.codigo.msbustamantepalomino.domain.aggregates.dto.PersonaDTO;
import com.codigo.msbustamantepalomino.domain.aggregates.requests.RequestPersona;
import com.codigo.msbustamantepalomino.domain.aggregates.response.ResponseReniec;
import com.codigo.msbustamantepalomino.domain.ports.out.PersonServiceOut;
import com.codigo.msbustamantepalomino.infrastructure.entity.PersonaEntity;
import com.codigo.msbustamantepalomino.infrastructure.entity.TipoDocumentoEntity;
import com.codigo.msbustamantepalomino.infrastructure.entity.TipoPersonaEntity;
import com.codigo.msbustamantepalomino.infrastructure.mapper.PersonaMapper;
import com.codigo.msbustamantepalomino.infrastructure.repository.PersonaRepository;
import com.codigo.msbustamantepalomino.infrastructure.repository.TipoDocumentoRepository;
import com.codigo.msbustamantepalomino.infrastructure.repository.TipoPersonaRepository;
import com.codigo.msbustamantepalomino.infrastructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonServiceOut {
    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;
    private final ClientReniec clientReniec;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public PersonaDTO crearPersonOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec =getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDTO(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long id) {
        return Optional.ofNullable(personaMapper.mapToDTO(personaRepository.findById(id).get()));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonDocuOut(String numDocu) {
        PersonaEntity persona = personaRepository.findByNumDocu(numDocu);
        return Optional.ofNullable(personaMapper.mapToDTO(persona));
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAll();
        for (PersonaEntity persona : entities) {
            PersonaDTO personaDTO = personaMapper.mapToDTO(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public List<PersonaDTO> obtenerTodosEstadoOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAllByEstado(1);
        for (PersonaEntity persona : entities) {
            PersonaDTO personaDTO = personaMapper.mapToDTO(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean exists =personaRepository.existsById(id);
        if(exists)
        {
            Optional<PersonaEntity>entity =personaRepository.findById(id);
            ResponseReniec responseReniec =getExecutionReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec,entity.get(),requestPersona));
            return personaMapper.mapToDTO(getEntityUpdate(responseReniec,entity.get(),requestPersona));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean exists =personaRepository.existsById(id);
        if(exists)
        {
            Optional<PersonaEntity>entity =personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDTO(entity.get());
        }
        return null;
    }

    private ResponseReniec getExecutionReniec(String numDoc) {
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = clientReniec.getInfoReniec(numDoc,authorization);
        return responseReniec;
    }
    private PersonaEntity getEntity(ResponseReniec responseReniec, RequestPersona requestPersona)
    {
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipoAndEstado(requestPersona.getTipoDoc(),1);
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipoAndEstado(requestPersona.getTipoPer(),1);
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(responseReniec.getNumeroDocumento());
        entity.setNombres(responseReniec.getNombres());
        entity.setApePat(responseReniec.getApellidoPaterno());
        entity.setApeMat(responseReniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResponseReniec responseReniec,PersonaEntity personaActualizar,RequestPersona requestPersona)
    {
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipoAndEstado(requestPersona.getTipoDoc(),1);
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipoAndEstado(requestPersona.getTipoPer(),1);
        personaActualizar.setNombres(responseReniec.getNombres());
        personaActualizar.setApePat(responseReniec.getApellidoPaterno());
        personaActualizar.setApeMat(responseReniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        personaActualizar.setTipoPersona(tipoPersona);
        personaActualizar.setTipoDocumento(tipoDocumento);
        return personaActualizar;
    }

    private Timestamp getTimestamp()
    {
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
