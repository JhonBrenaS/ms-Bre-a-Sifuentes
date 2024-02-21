package com.codigo.msBrenaSifuentes.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
//Crea atributos con valores nulos pero no los va a mostrar
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaDTO {
    private Long idEmpresa;
    private String numDocu;
    private String razonSocial;
    private String nombreComercial;
    private Integer estado;
    private String usuarioCreacion;
    private Timestamp fechaCreacion;
    private String usuarioModificacion;
    private Timestamp fechaModificacion;
    private String usuarioEliminacion;
    private Timestamp fechaEliminacion;
}
