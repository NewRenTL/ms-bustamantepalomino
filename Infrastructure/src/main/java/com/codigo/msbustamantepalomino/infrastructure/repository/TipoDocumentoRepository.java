package com.codigo.msbustamantepalomino.infrastructure.repository;

import com.codigo.msbustamantepalomino.infrastructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity,Long> {
    TipoDocumentoEntity findByCodTipoAndEstado(@Param("codTipo") String codTipo,Integer estado);
 }
