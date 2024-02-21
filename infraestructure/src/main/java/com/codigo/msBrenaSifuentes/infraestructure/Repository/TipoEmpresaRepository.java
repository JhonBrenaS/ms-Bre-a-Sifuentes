package com.codigo.msBrenaSifuentes.infraestructure.Repository;

import com.codigo.msBrenaSifuentes.infraestructure.Entity.TipoEmpresaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEmpresaRepository extends JpaRepository <TipoEmpresaEntity, Long> {
    TipoEmpresaEntity findByCodTipo(@Param("x") String codTipo);
}
