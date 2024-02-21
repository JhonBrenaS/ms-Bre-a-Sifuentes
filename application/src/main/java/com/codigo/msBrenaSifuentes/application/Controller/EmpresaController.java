package com.codigo.msBrenaSifuentes.application.Controller;

import com.codigo.msBrenaSifuentes.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msBrenaSifuentes.domain.aggregates.request.RequestEmpresa;
import com.codigo.msBrenaSifuentes.domain.ports.in.EmpresaServiceIn;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaServiceIn empresaServiceIn;


    @Operation(summary = "Api para crear una empresa")
    @PostMapping
    public ResponseEntity<EmpresaDTO> registrarEmpresa(@RequestBody RequestEmpresa requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(requestEmpresa));
    }

    @Operation(summary = "Api para obtener datos de una empresa")
    @GetMapping("/{numDoc}")
    public ResponseEntity<EmpresaDTO>obtenerEmpresa(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerEmpresaIn(numDoc).get());

    }
    @Operation(summary = "Api para obtener datos de todas las empresas Activas")
    @GetMapping()
    public ResponseEntity<List<EmpresaDTO>>obtenerTodos(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerTodosIn());

    }

    @Operation(summary = "Api para actualziar los datos de una empresa")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO>actualizarEmpresa(@PathVariable Long id,@RequestBody RequestEmpresa requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.actualizarIn(id,requestEmpresa));

    }

    @Operation(summary = "Api para Borrar datos de una empresa, eliminado logico Status =0")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDTO>deleteEmpresa(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.eliminarIn(id));

    }
}
