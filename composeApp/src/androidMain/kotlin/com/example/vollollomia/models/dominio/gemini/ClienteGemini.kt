package com.example.vollollomia.dominio.gemini

import android.graphics.Bitmap
import android.util.Base64
import com.example.vollollomia.dominio.nutricion.AlimentoDetectado
import com.example.vollollomia.dominio.ejercicio.PlanEjercicio
import com.example.vollollomia.dominio.dieta.PlanDieta
import com.example.vollollomia.Configuracion
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Cliente para interactuar con la API de Gemini.
 * Maneja análisis de imágenes, generación de planes de ejercicio y dieta.
 */
class ClienteGemini(
    private val claveSAPI: String = Configuracion.API_KEY_GEMINI
) {
    private val urlBase = "https://generativelanguage.googleapis.com/v1beta/models"
    private val modeloVision = "gemini-1.5-flash"
    private val modeloTexto = "gemini-1.5-flash"

    /**
     * Analiza una imagen de alimento utilizando Gemini Vision API
     */
    suspend fun analizarImagenAlimento(bitmap: Bitmap): AlimentoDetectado? = try {
        val imagenBase64 = bitmapABase64(bitmap)
        
        val jsonRequest = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("inline_data", JSONObject().apply {
                                put("mime_type", "image/jpeg")
                                put("data", imagenBase64)
                            })
                        })
                        put(JSONObject().apply {
                            put("text", "Analiza esta imagen de comida. Proporciona: nombre del alimento, cantidad estimada, calorías, proteínas (g), carbohidratos (g), grasas (g), fibra (g), sodio (mg), potasio (mg). Responde en formato JSON.")
                        })
                    })
                })
            })
        }

        val respuesta = hacerSolicitudHTTP(
            "$urlBase/$modeloVision:generateContent?key=$claveSAPI",
            jsonRequest.toString()
        )
        
        extraerAlimentoDeJSON(respuesta)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /**
     * Genera un plan de ejercicio basado en los datos del usuario
     */
    suspend fun generarPlanEjercicio(
        objetivo: String,
        duracionSemanas: Int
    ): PlanEjercicio? = try {
        val solicitud = "Genera un plan de ejercicio de $duracionSemanas semanas para objetivo de $objetivo. " +
            "Incluye: nombre del plan, duración, sesiones por semana, descripción. Responde en JSON."
        
        val jsonRequest = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", solicitud)
                        })
                    })
                })
            })
        }

        val respuesta = hacerSolicitudHTTP(
            "$urlBase/$modeloTexto:generateContent?key=$claveSAPI",
            jsonRequest.toString()
        )
        
        extraerPlanEjercicioDeJSON(respuesta, objetivo)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /**
     * Genera un plan de dieta personalizado
     */
    suspend fun generarPlanDieta(
        calorias: Double,
        tipo: String
    ): PlanDieta? = try {
        val solicitud = "Genera un plan de dieta $tipo con objetivo de $calorias calorías por día. " +
            "Incluye: tipo de dieta, objetivo calórico, descripción. Responde en JSON."
        
        val jsonRequest = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", solicitud)
                        })
                    })
                })
            })
        }

        val respuesta = hacerSolicitudHTTP(
            "$urlBase/$modeloTexto:generateContent?key=$claveSAPI",
            jsonRequest.toString()
        )
        
        extraerPlanDietaDeJSON(respuesta)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /**
     * Realiza una solicitud HTTP POST a la API de Gemini
     */
    private fun hacerSolicitudHTTP(url: String, body: String): String {
        val conexion = URL(url).openConnection() as HttpURLConnection
        return try {
            conexion.apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                doOutput = true
                outputStream.write(body.toByteArray())
                outputStream.flush()
            }

            if (conexion.responseCode == HttpURLConnection.HTTP_OK) {
                conexion.inputStream.bufferedReader().use { it.readText() }
            } else {
                ""
            }
        } finally {
            conexion.disconnect()
        }
    }

    /**
     * Convierte un Bitmap a Base64
     */
    private fun bitmapABase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    /**
     * Extrae información de alimento del JSON de respuesta
     */
    private fun extraerAlimentoDeJSON(jsonResponse: String): AlimentoDetectado? {
        return try {
            val json = JSONObject(jsonResponse)
            val contenido = json.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONArray("content")
                .getJSONObject(0)
                .getString("text")
            
            val foodJson = JSONObject(extraerJSON(contenido))
            AlimentoDetectado(
                nombreAlimento = foodJson.optString("nombre", "Alimento"),
                calorias = foodJson.optDouble("calorias", 0.0),
                proteinas = foodJson.optDouble("proteinas", 0.0),
                carbohidratos = foodJson.optDouble("carbohidratos", 0.0),
                grasas = foodJson.optDouble("grasas", 0.0),
                fibra = foodJson.optDouble("fibra", 0.0),
                sodio = foodJson.optDouble("sodio", 0.0),
                potasio = foodJson.optDouble("potasio", 0.0)
            )
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Extrae información de plan de ejercicio del JSON
     */
    private fun extraerPlanEjercicioDeJSON(jsonResponse: String, objetivo: String): PlanEjercicio? {
        return try {
            val json = JSONObject(jsonResponse)
            val contenido = json.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONArray("content")
                .getJSONObject(0)
                .getString("text")
            
            val planJson = JSONObject(extraerJSON(contenido))
            PlanEjercicio(
                nombre = planJson.optString("nombre", "Plan de Entrenamiento"),
                duracionSemanas = planJson.optInt("duracion_semanas", 12),
                sesionesxSemana = planJson.optInt("sesiones_semana", 3),
                descripcion = planJson.optString("descripcion", ""),
                sesiones = emptyList(),
                objetivo = objetivo
            )
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Extrae información de plan de dieta del JSON
     */
    private fun extraerPlanDietaDeJSON(jsonResponse: String): PlanDieta? {
        return try {
            val json = JSONObject(jsonResponse)
            val contenido = json.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONArray("content")
                .getJSONObject(0)
                .getString("text")
            
            val dietJson = JSONObject(extraerJSON(contenido))
            PlanDieta(
                tipoDieta = dietJson.optString("tipo", "Balanceada"),
                objetivoCaloriasDiarias = dietJson.optDouble("calorias_diarias", 2000.0),
                descripcion = dietJson.optString("descripcion", ""),
                dias = emptyList(),
                recetas = emptyList()
            )
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Extrae un bloque JSON de texto que puede estar embebido
     */
    private fun extraerJSON(texto: String): String {
        val inicio = texto.indexOf('{')
        val fin = texto.lastIndexOf('}')
        return if (inicio != -1 && fin != -1 && fin > inicio) {
            texto.substring(inicio, fin + 1)
        } else {
            "{}"
        }
    }
}
