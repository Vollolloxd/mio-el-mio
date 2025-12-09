package com.example.vollollomia.dominio.ejercicio

import com.example.vollollomia.dominio.nutricion.PerfilUsuario

/**
 * Clase base para tipos de ejercicio.
 */
sealed class TipoEjercicio {
    data object Cardio : TipoEjercicio()
    data object Musculacion : TipoEjercicio()
    data object Flexibilidad : TipoEjercicio()
    data object Funcional : TipoEjercicio()
}

/**
 * Intensidad del ejercicio.
 */
sealed class IntensidadEjercicio {
    data object Baja : IntensidadEjercicio()
    data object Moderada : IntensidadEjercicio()
    data object Alta : IntensidadEjercicio()
}

/**
 * Representa un ejercicio individual con sus detalles.
 */
data class Ejercicio(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val tipo: TipoEjercicio,
    val intensidad: IntensidadEjercicio,
    val duracionMinutos: Int,
    val caloriasBajoxMinuto: Double,
    val instrucciones: List<String>,
    val imagenUrl: String = "",
    val videoUrl: String = ""
) {
    fun calcularCaloriasQuemadas(): Double {
        return duracionMinutos * caloriasBajoxMinuto
    }
}

/**
 * Sesión de entrenamiento con múltiples ejercicios.
 */
data class SesionEntrenamiento(
    val id: String,
    val nombre: String,
    val fecha: String,
    val ejercicios: List<Ejercicio>,
    val tiempoTotalMinutos: Int = 0,
    val completada: Boolean = false
) {
    fun calcularCaloriasQuemadas(): Double {
        return ejercicios.sumOf { it.calcularCaloriasQuemadas() }
    }
    
    fun obtenerTiempoTotal(): Int {
        return ejercicios.sumOf { it.duracionMinutos }
    }
}

/**
 * Plan de ejercicio personalizado basado en el objetivo del usuario.
 */
data class PlanEjercicio(
    val id: String,
    val usuarioId: String,
    val nombrePlan: String,
    val descripcion: String,
    val duracionSemanas: Int,
    val diasPorSemana: Int,
    val sesiones: List<SesionEntrenamiento>,
    val fechaInicio: String,
    val fechaFin: String,
    val completado: Boolean = false
) {
    fun obtenerProgresoGlobal(): Double {
        if (sesiones.isEmpty()) return 0.0
        val completadas = sesiones.count { it.completada }
        return (completadas.toDouble() / sesiones.size) * 100
    }
    
    fun calcularCaloriasQuemadasTotal(): Double {
        return sesiones.sumOf { it.calcularCaloriasQuemadas() }
    }
}

/**
 * Histórico y progreso del entrenamiento del usuario.
 */
data class HistorialEjercicio(
    val usuarioId: String,
    val planes: List<PlanEjercicio>,
    val sesionesCompletadas: List<SesionEntrenamiento> = emptyList()
) {
    fun obtenerCaloriasQuemadasMesActual(): Double {
        return sesionesCompletadas
            .filter { estaEnMesActual(it.fecha) }
            .sumOf { it.calcularCaloriasQuemadas() }
    }
    
    fun obtenerPlanActivo(): PlanEjercicio? {
        return planes.firstOrNull { !it.completado }
    }
    
    private fun estaEnMesActual(fecha: String): Boolean {
        // TODO: Implementar lógica para comparar fechas
        return true
    }
}
