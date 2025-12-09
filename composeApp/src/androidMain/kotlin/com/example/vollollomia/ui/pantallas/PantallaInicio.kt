package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.ui.tema.Colores

/**
 * Pantalla principal de Mio-el-Mio.
 * Muestra opciones de navegación a: escaneo, datos, dieta, ejercicios.
 */
@Composable
fun PantallaInicio(
    onNavigarEscaneo: () -> Unit,
    onNavigarDatos: () -> Unit,
    onNavigarDieta: () -> Unit,
    onNavigarEjercicios: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.Fondo)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mio-el-Mio",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Colores.TemaPrincipal,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Tu compañero de nutrición y fitness",
            fontSize = 14.sp,
            color = Colores.TextoSecundario,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        BotonInicio("Escanear Alimentos", Colores.ProteinasRosa, onNavigarEscaneo)
        BotonInicio("Mi Perfil", Colores.AzulPastel, onNavigarDatos)
        BotonInicio("Mi Dieta", Colores.AmarilloPerla, onNavigarDieta)
        BotonInicio("Mis Ejercicios", Colores.VerdePerla, onNavigarEjercicios)
    }
}

@Composable
private fun BotonInicio(
    texto: String,
    color: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Colores.TextoPrincipal
        )
    ) {
        Text(text = texto, fontWeight = FontWeight.Bold)
    }
}
