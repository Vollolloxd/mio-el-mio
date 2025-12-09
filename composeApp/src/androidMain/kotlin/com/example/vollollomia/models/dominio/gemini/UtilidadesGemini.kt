package com.example.vollollomia.dominio.gemini

import com.example.vollollomia.dominio.nutricion.AlimentoDetectado

/**
 * Utilidades para parsear y procesar respuestas de Gemini API.
 */
object UtilidadesGemini {
    
    /**
     * Valida que la respuesta de Gemini sea valida JSON.
     */
    fun validarRespuesta(respuesta: String): Boolean {
        return respuesta.isNotBlank() && (
            respuesta.startsWith("{") || respuesta.startsWith("[")
        )
    }
    
    /**
     * Extrae la parte JSON de una respuesta de Gemini.
     */
    fun extraerJSON(respuesta: String): String? {
        return try {
            val inicioJson = respuesta.indexOf("{")
            val finJson = respuesta.lastIndexOf("}")
            
            if (inicioJson >= 0 && finJson > inicioJson) {
                respuesta.substring(inicioJson, finJson + 1)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Crea un AlimentoDetectado de ejemplo para pruebas.
     */
    fun crearAlimentoEjemplo(): AlimentoDetectado {
        return AlimentoDetectado(
            nombre = "Manzana Roja",
            descripcion = "Manzana fresca y crujiente",
            calorias = 52.0,
            proteinas = 0.3,
            carbohidratos = 14.0,
            grasas = 0.2,
            fibra = 2.4,
            porcion = "100g (1 manzana mediana)",
            vitaminas = listOf("Vitamina C", "Vitamina A"),
            minerales = listOf("Potasio", "Magnesio")
        )
    }
    
    /**
     * Verifica que los datos de un alimento sean validos.
     */
    fun validarAlimento(alimento: AlimentoDetectado): Boolean {
        return alimento.nombre.isNotBlank() &&
                alimento.calorias >= 0 &&
                alimento.proteinas >= 0 &&
                alimento.carbohidratos >= 0 &&
                alimento.grasas >= 0 &&
                alimento.fibra >= 0
    }
    
    /**
     * Calcula macronutrientes totales de una lista de alimentos.
     */
    fun calcularMacrosTotal(alimentos: List<AlimentoDetectado>): Triple<Double, Double, Double> {
        val proteinasTotal = alimentos.sumOf { it.proteinas }
        val carbohidratosTotal = alimentos.sumOf { it.carbohidratos }
        val grasasTotal = alimentos.sumOf { it.grasas }
        return Triple(proteinasTotal, carbohidratosTotal, grasasTotal)
    }
}
