package com.example.lab1mvc.service;

import com.example.lab1mvc.dto.TareaDTO;
import com.example.lab1mvc.model.Tarea;
import com.example.lab1mvc.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class TareaService {

    @Autowired
    private TareaRepository repository;

    public List<Tarea> listarTodas() {
        return repository.findAll();
    }

    public Tarea crearTarea(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setPrioridad(dto.getPrioridad());
        return repository.save(tarea);
    }

    public List<Tarea> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public Tarea actualizar(Long id, TareaDTO dto) {
        Tarea tareaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));

        tareaExistente.setTitulo(dto.getTitulo());
        tareaExistente.setDescripcion(dto.getDescripcion());
        tareaExistente.setEstado(dto.getEstado());
        tareaExistente.setPrioridad(dto.getPrioridad());

        return repository.save(tareaExistente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Tarea no encontrada");
        }
        repository.deleteById(id);
    }

    public Map<String, Long> obtenerResumenPorEstado() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Tarea::getEstado, Collectors.counting()));
    }

    public Tarea obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada")); // Esto activará el error 404 si no existe [cite: 151]
    }

    public Page<Tarea> listarPaginado(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}   