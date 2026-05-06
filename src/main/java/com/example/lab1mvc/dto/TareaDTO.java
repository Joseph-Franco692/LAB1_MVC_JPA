package com.example.lab1mvc.dto;

import jakarta.validation.constraints.*;

public class TareaDTO {
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @Size(min = 10, message = "La descripción debe ser más larga")
    private String descripcion;

    @NotBlank
    private String estado;

    @Min(1) @Max(5)
    private Integer prioridad;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}