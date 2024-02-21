package com.codigo.msBrenaSifuentes.infraestructure.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "TipoEmpresaEntity.findByCodTipo", query = "SELECT a from TipoEmpresaEntity a where a.codTipo = :codTipo")
@Entity
@Getter
@Setter
@Table(name = "tipo_empresa")
public class TipoEmpresaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_tipo_empresa")
        private Long idTipoEmpresa;

        @Column(name = "cod_tipo", nullable = false)
        private String codTipo;

        @Column(name = "desc_tipo", nullable = false)
        private String descripcionTipo;

        @Column(name = "estado", nullable = false)
        private Integer estado;

        @Column(name = "usua_crea")
        private String usuarioCreacion;

        @Column(name = "date_create")
        private Timestamp fechaCreacion;

        @Column(name = "usua_modif")
        private String usuarioModificacion;

        @Column(name = "date_modif")
        private Timestamp fechaModificacion;

        @Column(name = "usua_delet")
        private String usuarioEliminacion;

        @Column(name = "date_delet")
        private Timestamp fechaEliminacion;
    }

