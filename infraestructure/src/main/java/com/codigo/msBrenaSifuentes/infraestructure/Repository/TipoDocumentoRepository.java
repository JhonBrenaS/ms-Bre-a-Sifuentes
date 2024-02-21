package com.codigo.msBrenaSifuentes.infraestructure.Repository;

import com.codigo.msBrenaSifuentes.infraestructure.Entity.TipoDocumentoEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository <TipoDocumentoEntity, Long> {
    TipoDocumentoEntity findByCodTipo(@Param("codTipo") String codTipo);
}
