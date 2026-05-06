package com.example.lab1mvc.repository;

import com.example.lab1mvc.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByEstado(String estado);

    @Query("SELECT t FROM Tarea t WHERE t.prioridad > 3")
    List<Tarea> findTareasPrioritarias();
}