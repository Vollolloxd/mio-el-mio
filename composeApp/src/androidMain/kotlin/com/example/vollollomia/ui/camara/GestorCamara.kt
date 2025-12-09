package com.example.vollollomia.ui.camara

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat

/**
 * Gestor de cámara para capturar y procesar imágenes
 * Maneja permisos y proporciona acceso a la cámara del dispositivo
 */
class GestorCamara(private val contexto: Context) {
    
    /**
     * Verifica si la aplicación tiene permiso de cámara
     */
    fun tienePermisosCamara(): Boolean {
        return ContextCompat.checkSelfPermission(
            contexto,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Obtiene la lista de permisos requeridos
     */
    fun obtenerPermisosRequeridos(): Array<String> {
        return arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}

/**
 * Composable para solicitar permisos de cámara
 */
@Composable
fun SolicitadorPermisosCamara(
    onPermisosOtorgados: () -> Unit,
    onPermisosDenegados: () -> Unit,
    contenido: @Composable () -> Unit
) {
    val solicitarPermisos = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        val todosOtorgados = permisos.values.all { it }
        if (todosOtorgados) {
            onPermisosOtorgados()
        } else {
            onPermisosDenegados()
        }
    }
    
    LaunchedEffect(Unit) {
        solicitarPermisos.launch(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ))
    }
    
    contenido()
}

/**
 * Composable para capturar imágenes de cámara
 * Devuelve un Bitmap de la imagen capturada
 */
@Composable
fun CaptorCamara(
    onImagenCapturada: (Bitmap) -> Unit,
    onCancelado: () -> Unit
) {
    val capturadorFoto = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            onImagenCapturada(bitmap)
        } else {
            onCancelado()
        }
    }
    
    LaunchedEffect(Unit) {
        capturadorFoto.launch()
    }
}
