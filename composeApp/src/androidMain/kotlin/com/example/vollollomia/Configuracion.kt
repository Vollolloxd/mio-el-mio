package com.example.vollollomia

/**
 * Configuracion centralizada de la aplicacion Mio-el-Mio.
 * IMPORTANTE: Reemplaza TU_API_KEY_AQUI con tu clave Gemini Pro.
 */
object Configuracion {
    // ========== GEMINI API ==========
    // Obtén tu API key desde: https://aistudio.google.com
    // 1. Ve a AI Studio
    // 2. Copia tu API key de Gemini Pro
    // 3. Reemplaza el valor abajo:
    
    const val API_KEY_GEMINI = "TU_API_KEY_AQUI"
    const val URL_BASE_GEMINI = "https://generativelanguage.googleapis.com/v1beta/models"
    const val MODELO_GEMINI = "gemini-pro-vision"
    
    // ========== CONFIGURACION APP ==========
    const val NOMBRE_APP = "Mio-el-Mio"
    const val VERSION_APP = "1.0.0"
    
    // Nutricion
    const val PORCIONES_ESTANDAR = "100g"
    const val MACROS_PREDETERMINADOS = 2000.0
    
    // Ejercicio
    const val DURACION_PLAN_DEFECTO = 4
    const val DIAS_ENTRENAMIENTO_SEMANA = 3
    
    // Dieta
    const val DURACION_PLAN_DIETA_DEFECTO = 28
    
    /**
     * Valida que la API key haya sido configurada.
     */
    fun esApiKeyConfigurada(): Boolean {
        return API_KEY_GEMINI.isNotEmpty() && 
               API_KEY_GEMINI != "TU_API_KEY_AQUI"
    }
}
