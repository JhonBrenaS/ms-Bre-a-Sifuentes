package com.codigo.msBrenaSifuentes.domain.ports.out;

import com.codigo.msBrenaSifuentes.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msBrenaSifuentes.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaOut (String numDoc);
    List<EmpresaDTO> obtenerTodosOut();
    EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa);
    EmpresaDTO eliminarOut(Long id);
}
