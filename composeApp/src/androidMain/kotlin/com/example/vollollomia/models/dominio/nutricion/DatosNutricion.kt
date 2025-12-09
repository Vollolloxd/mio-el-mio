package com.example.vollollomia.dominio.nutricion

/**
 * Clase base para toda información nutricional.
 * Implementa polimorfismo para diferentes tipos de datos nutricionales.
 */
open class InformacionNutricional {
    open val calorias: Double = 0.0
    open val proteinas: Double = 0.0
    open val carbohidratos: Double = 0.0
    open val grasas: Double = 0.0
    open val fibra: Double = 0.0
}

/**
 * Representa un alimento detectado por la cámara usando Gemini API.
 */
data class AlimentoDetectado(
    val nombre: String,
    val descripcion: String,
    override val calorias: Double,
    override val proteinas: Double,
    override val carbohidratos: Double,
    override val grasas: Double,
    override val fibra: Double,
    val porcion: String = "100g",
    val vitaminas: List<String> = emptyList(),
    val minerales: List<String> = emptyList()
) : InformacionNutricional()

/**
 * Registro diario de nutrición del usuario.
 */
data class RegistroNutricionalDiario(
    val fecha: String,
    val alimentosConsumidos: List<AlimentoDetectado>,
    val caloriasTotales: Double = 0.0,
    val proteinas: Double = 0.0,
    val carbohidratos: Double = 0.0,
    val grasas: Double = 0.0
) : InformacionNutricional() {
    override val calorias: Double
        get() = alimentosConsumidos.sumOf { it.calorias }
    
    override val proteinas: Double
        get() = alimentosConsumidos.sumOf { it.proteinas }
    
    override val carbohidratos: Double
        get() = alimentosConsumidos.sumOf { it.carbohidratos }
    
    override val grasas: Double
        get() = alimentosConsumidos.sumOf { it.grasas }
    
    override val fibra: Double
        get() = alimentosConsumidos.sumOf { it.fibra }
}

/**
 * Objetivo físico del usuario - Usa sealed class para herencia segura.
 */
sealed class ObjetivoFisico {
    data object GananciaDeMasa : ObjetivoFisico()
    data object HiperTrofia : ObjetivoFisico()
    data object Definicion : ObjetivoFisico()
    data object Mantenimiento : ObjetivoFisico()
}

/**
 * Perfil del usuario con sus datos personales y objetivos.
 */
data class PerfilUsuario(
    val id: String,
    val nombre: String,
    val altura: Double,
    val peso: Double,
    val edad: Int,
    val sexo: String,
    val objetivo: ObjetivoFisico,
    val nivelActividad: String,
    val historicalNutricion: List<RegistroNutricionalDiario> = emptyList()
) {
    fun calcularIMC(): Double {
        val alturaEnMetros = altura / 100.0
        return peso / (alturaEnMetros * alturaEnMetros)
    }
    
    fun calcularTasaMetabolicaBasal(): Double {
        return if (sexo == "M") {
            88.362 + (13.397 * peso) + (4.799 * altura) - (5.677 * edad)
        } else {
            447.593 + (9.247 * peso) + (3.098 * altura) - (4.330 * edad)
        }
    }
    
    fun calcularGastoCalorioDiario(): Double {
        val tmb = calcularTasaMetabolicaBasal()
        val factor = when (nivelActividad) {
            "sedentario" -> 1.2
            "ligero" -> 1.375
            "moderado" -> 1.55
            "activo" -> 1.725
            "muy_activo" -> 1.9
            else -> 1.5
        }
        return tmb * factor
    }
}

/**
 * Meta nutricional personalizada basada en el perfil y objetivo del usuario.
 */
data class MetaNutricional(
    val usuarioId: String,
    val caloriasDiarias: Double,
    val proteinasGramos: Double,
    val carbohidratosGramos: Double,
    val grasasGramos: Double,
    val fibraGramos: Double,
    val fechaInicio: String,
    val fechaFin: String
)
