package com.example.vollollomia.persistencia.preferencias

import android.content.Context
import android.content.SharedPreferences
import com.example.vollollomia.dominio.nutricion.PerfilUsuario, ObjetivoFisico

/**
 * Gestor de preferencias del usuario usando SharedPreferences.
 * Permite guardar y cargar datos del usuario de forma persistente.
 */
class PreferenciasUsuario(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "PreferenciasApp",
        Context.MODE_PRIVATE
    )
    
    fun guardarPerfilUsuario(perfil: PerfilUsuario) {
        prefs.edit().apply {
            putString(KEY_USUARIO_ID, perfil.id)
            putString(KEY_NOMBRE, perfil.nombre)
            putFloat(KEY_ALTURA, perfil.altura.toFloat())
            putFloat(KEY_PESO, perfil.peso.toFloat())
            putInt(KEY_EDAD, perfil.edad)
            putString(KEY_SEXO, perfil.sexo)
            putString(KEY_OBJETIVO, perfil.objetivo.toString())
            putString(KEY_NIVEL_ACTIVIDAD, perfil.nivelActividad)
            apply()
        }
    }
    
    fun obtenerPerfilUsuario(): PerfilUsuario? {
        val id = prefs.getString(KEY_USUARIO_ID, null) ?: return null
        val nombre = prefs.getString(KEY_NOMBRE, "") ?: ""
        val altura = prefs.getFloat(KEY_ALTURA, 0f).toDouble()
        val peso = prefs.getFloat(KEY_PESO, 0f).toDouble()
        val edad = prefs.getInt(KEY_EDAD, 0)
        val sexo = prefs.getString(KEY_SEXO, "M") ?: "M"
        val objetivo = ObjetivoFisico.GananciaDeMasa
        val nivelActividad = prefs.getString(KEY_NIVEL_ACTIVIDAD, "moderado") ?: "moderado"
        
        return PerfilUsuario(
            id = id,
            nombre = nombre,
            altura = altura,
            peso = peso,
            edad = edad,
            sexo = sexo,
            objetivo = objetivo,
            nivelActividad = nivelActividad
        )
    }
    
    fun limpiarPreferencias() {
        prefs.edit().clear().apply()
    }
    
    companion object {
        private const val KEY_USUARIO_ID = "usuario_id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_ALTURA = "altura"
        private const val KEY_PESO = "peso"
        private const val KEY_EDAD = "edad"
        private const val KEY_SEXO = "sexo"
        private const val KEY_OBJETIVO = "objetivo"
        private const val KEY_NIVEL_ACTIVIDAD = "nivel_actividad"
    }
}
