package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.ui.tema.Colores

@Composable
fun PantallaInicio(
    onNavEscaneo: () -> Unit = {},
    onNavDatos: () -> Unit = {},
    onNavDieta: () -> Unit = {},
    onNavEjercicios: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.fondoPrincipal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mio-el-Mío",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Colores.TemaPrincipal,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Tu asistente de nutrición y fitness",
            fontSize = 16.sp,
            color = Colores.textoSecundario,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        BotonInicio(
            texto = "Escáner de Alimentos",
            onClick = onNavEscaneo
        )
        
        BotonInicio(
            texto = "Datos de Usuario",
            onClick = onNavDatos
        )
        
        BotonInicio(
            texto = "Plan de Dieta",
            onClick = onNavDieta
        )
        
        BotonInicio(
            texto = "Plan de Ejercicios",
            onClick = onNavEjercicios
        )
    }
}

@Composable
fun BotonInicio(
    texto: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 8.dp),
        backgroundColor = Colores.acentoPrincipal
    ) {
        Text(
            text = texto,
            color = Colores.textoBoton,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
