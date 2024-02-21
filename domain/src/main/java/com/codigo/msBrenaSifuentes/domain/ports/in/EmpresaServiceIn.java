package com.codigo.msBrenaSifuentes.domain.ports.in;

import com.codigo.msBrenaSifuentes.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msBrenaSifuentes.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaIn (String numDoc);
    List<EmpresaDTO> obtenerTodosIn();
    EmpresaDTO actualizarIn (Long id, RequestEmpresa requestEmpresa);
    EmpresaDTO eliminarIn (Long id);
}
