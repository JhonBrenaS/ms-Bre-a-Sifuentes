package com.codigo.msBrenaSifuentes.infraestructure.Adapters;

import com.codigo.msBrenaSifuentes.domain.aggregates.constants.Constants;
import com.codigo.msBrenaSifuentes.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msBrenaSifuentes.domain.aggregates.request.RequestEmpresa;
import com.codigo.msBrenaSifuentes.domain.aggregates.response.ResponseSunat;
import com.codigo.msBrenaSifuentes.domain.ports.out.EmpresaServiceOut;
import com.codigo.msBrenaSifuentes.infraestructure.Entity.EmpresaEntity;
import com.codigo.msBrenaSifuentes.infraestructure.Entity.TipoDocumentoEntity;
import com.codigo.msBrenaSifuentes.infraestructure.Entity.TipoEmpresaEntity;
import com.codigo.msBrenaSifuentes.infraestructure.Mapper.EmpresaMapper;
import com.codigo.msBrenaSifuentes.infraestructure.Repository.EmpresaRepository;
import com.codigo.msBrenaSifuentes.infraestructure.Repository.TipoDocumentoRepository;
import com.codigo.msBrenaSifuentes.infraestructure.Repository.TipoEmpresaRepository;
import com.codigo.msBrenaSifuentes.infraestructure.Rest.Client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {
    private final EmpresaRepository empresaRepository;
    private  final TipoDocumentoRepository tipoDocumentoRepository;
    private  final TipoEmpresaRepository tipoEmpresaRepository;
    private final ClienteSunat clienteSunat;
    private final EmpresaMapper empresaMapper;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa) {
        ResponseSunat responseSunat = getInfo(requestEmpresa.getNumDocu());
        empresaRepository.save(getEntity(responseSunat,requestEmpresa));
        return empresaMapper.mapToDto(getEntity(responseSunat,requestEmpresa));
    }

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaOut(String numDocu) {
        EmpresaDTO eDTO = empresaMapper.mapToDto(empresaRepository.findByNumDocu(numDocu));
        return Optional.of(eDTO);
    }

    @Override
    public List<EmpresaDTO> obtenerTodosOut() {
        List <EmpresaDTO> empresaDTOS = new ArrayList<>();
        List<EmpresaEntity> entityList = empresaRepository.findByEstado(Constants.STATUS_ACTIVE);
        for (EmpresaEntity entity : entityList){
            EmpresaDTO empresaDTO = empresaMapper.mapToDto(entity);
            empresaDTOS.add(empresaDTO);
        }
        return empresaDTOS;
    }


    @Override
    public EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa) {
        boolean existe = empresaRepository.existsById(id);
        if (existe){
            Optional <EmpresaEntity> empresa = empresaRepository.findById(id);
            ResponseSunat responseSunat = getInfo(requestEmpresa.getNumDocu());
            empresaRepository.save(getEntityUpdate(responseSunat, empresa.get()));
            return empresaMapper.mapToDto(getEntityUpdate(responseSunat, empresa.get()));
        }
        return null;
    }

    @Override
    public EmpresaDTO eliminarOut(Long id) {
        boolean existe = empresaRepository.existsById(id);
        if (existe){
            Optional <EmpresaEntity> empresa = empresaRepository.findById(id);
            empresa.get().setEstado(Constants.STATUS_INACTIVE);
            empresa.get().setUsuarioEliminacion(Constants.AUDIT_ADMIN);
            empresa.get().setFechaEliminacion(getTimestamp());
            empresaRepository.save(empresa.get());
            return empresaMapper.mapToDto(empresa.get());
        }
        return null;
    }

    private ResponseSunat getInfo(String numero){
        String autho = "Bearer" + tokenApi;
        return clienteSunat.getInfoSunat(numero,autho);
    }

    //metodos
    private EmpresaEntity getEntity(ResponseSunat sunat, RequestEmpresa requestEmpresa){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestEmpresa.getTipoDoc());
        TipoEmpresaEntity tipoEmpresa = tipoEmpresaRepository.findByCodTipo(requestEmpresa.getTipoEmp());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumDocu(sunat.getNumeroDocumento());
        entity.setRazonSocial(sunat.getRazonSocial());
        entity.setNombreComercial(sunat.getViaNombre());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuarioCreacion(Constants.AUDIT_ADMIN);
        entity.setFechaCreacion(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoEmpresa(tipoEmpresa);
        return entity;
    }

    private EmpresaEntity getEntityUpdate(ResponseSunat sunat, EmpresaEntity empresaUpdate){
        empresaUpdate.setNumDocu(sunat.getNumeroDocumento());
        empresaUpdate.setRazonSocial(sunat.getRazonSocial());
        empresaUpdate.setNombreComercial(sunat.getViaNombre());
        empresaUpdate.setUsuarioModificacion(Constants.AUDIT_ADMIN);
        empresaUpdate.setFechaModificacion(getTimestamp());
        return empresaUpdate;
    }


    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}