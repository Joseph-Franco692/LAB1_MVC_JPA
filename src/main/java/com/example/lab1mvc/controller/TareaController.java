package com.example.lab1mvc.controller;

import com.example.lab1mvc.dto.TareaDTO;
import com.example.lab1mvc.model.Tarea;
import com.example.lab1mvc.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @GetMapping
    public List<Tarea> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody TareaDTO dto) {
        Tarea nuevaTarea = service.crearTarea(dto);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @Valid @RequestBody TareaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/filtrar")
    public List<Tarea> filtrarPorEstado(@RequestParam String estado) {
        return service.buscarPorEstado(estado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/paginado")
    public Page<Tarea> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.listarPaginado(page, size);
    }

    @GetMapping("/resumen")
    public Map<String, Long> obtenerResumen() {
        return service.obtenerResumenPorEstado();
    }
}