package com.example.vollollomia.dominio.dieta

import com.example.vollollomia.dominio.nutricion.MetaNutricional

/**
 * Tipo de dieta según el objetivo.
 */
sealed class TipoDieta {
    data object DeficitCalorico : TipoDieta()
    data object Mantenimiento : TipoDieta()
    data object Superavit : TipoDieta()
    data object IntervalFasting : TipoDieta()
}

/**
 * Receta nutricional con detalles de ingredientes y preparación.
 */
data class Receta(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val ingredientes: List<Ingrediente>,
    val modoPreparacion: List<String>,    
        val tiempoPreparacionMinutos: Int,
    
    val porciones: Int,
    val caloriasPorPorcion: Double,
    val proteinasPorPorcion: Double,
    val carbohidratosPorPorcion: Double,
    val grasasPorPorcion: Double,
    val dificultad: String = "media", // fácil, media, difícil
    val imagenUrl: String = ""
)

/**
 * Ingrediente de una receta.
 */
data class Ingrediente(
    val nombre: String,
    val cantidad: Double,
    val unidad: String, // g, ml, taza, cucharada, etc
    val calorias: Double = 0.0
)

/**
 * Comida del día.
 */
data class Comida(
    val id: String,
    val nombre: String,
    val tipo: String, // desayuno, almuerzo, merienda, cena
    val recetas: List<Receta>,
    val horaRecomendada: String = ""
) {
    fun calcularCaloriasTotal(): Double = recetas.sumOf { it.caloriasPorPorcion }
    fun calcularProteinasTotal(): Double = recetas.sumOf { it.proteinasPorPorcion }
    fun calcularCarbohidratosTotal(): Double = recetas.sumOf { it.carbohidratosPorPorcion }
    fun calcularGrasasTotal(): Double = recetas.sumOf { it.grasasPorPorcion }
}

/**
 * Día de dieta con múltiples comidas.
 */
data class DiaDieta(
    val fecha: String,
    val comidas: List<Comida>,
    val meta: MetaNutricional?
) {
    fun calcularCaloriasTotal(): Double = comidas.sumOf { it.calcularCaloriasTotal() }
    fun calcularProgresoCalorio(): Double {
        val calorias = calcularCaloriasTotal()
        val metaCalorias = meta?.caloriasDiarias ?: return 0.0
        return (calorias / metaCalorias) * 100
    }
}

/**
 * Plan de dieta personalizado para el usuario.
 */
data class PlanDieta(
    val id: String,
    val usuarioId: String,
    val nombre: String,
    val descripcion: String,
    val tipo: TipoDieta,
    val duracionDias: Int,
    val diasDieta: List<DiaDieta>,
    val fechaInicio: String,
    val fechaFin: String,
    val completado: Boolean = false
) {
    fun obtenerProgresoGlobal(): Double {
        if (diasDieta.isEmpty()) return 0.0
        val diasCompletados = diasDieta.count { it.comidas.isNotEmpty() }
        return (diasCompletados.toDouble() / diasDieta.size) * 100
    }
    
    fun calcularCaloriasPlaneadas(): Double {
        return diasDieta.sumOf { dia -> dia.meta?.caloriasDiarias ?: 0.0 }
    }
}

/**
 * Historial de segu imiento de dieta del usuario.
 */
data class HistorialDieta(
    val usuarioId: String,
    val planes: List<PlanDieta>,
    val diasRegistrados: List<DiaDieta> = emptyList()
) {
    fun obtenerPlanActivo(): PlanDieta? {
        return planes.firstOrNull { !it.completado }
    }
    
    fun calcularPromedioCaloricoDiario(): Double {
        if (diasRegistrados.isEmpty()) return 0.0
        val totalCalorias = diasRegistrados.sumOf { it.calcularCaloriasTotal() }
        return totalCalorias / diasRegistrados.size
    }
}
