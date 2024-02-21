package com.codigo.msBrenaSifuentes.infraestructure.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "EmpresaEntity.findByNumDocu", query = "SELECT a from EmpresaEntity a where a.numDocu=:numDocu")
@NamedQuery(name = "EmpresaEntity.findByEstado", query = "SELECT a from EmpresaEntity a where a.estado=:estado")
@Entity
@Getter
@Setter
@Table(name = "empresa")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;
    @Column(name = "num_docu", nullable = false)
    private String numDocu;
    @Column(name = "razon_social", nullable = false)
    private String razonSocial;
    @Column(name = "nom_comercial", nullable = false)
    private String nombreComercial;
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
    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", referencedColumnName = "id_tipo_documento")
    private TipoDocumentoEntity tipoDocumento;
    @ManyToOne
    @JoinColumn(name = "tipo_empresa_id", referencedColumnName = "id_tipo_empresa")
    private TipoEmpresaEntity tipoEmpresa;
}
