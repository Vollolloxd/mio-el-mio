package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.models.dominio.ejercicio.PlanEjercicio
import com.example.vollollomia.models.dominio.ejercicio.SesionEntrenamiento
import com.example.vollollomia.ui.tema.Colores

@Composable
fun PantallaEjercicios(
    onVolverAlInicio: () -> Unit
) {
    var planEjercicio by remember { mutableStateOf<PlanEjercicio?>(null) }
    var generando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.fondoPrincipal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plan de Ejercicios",
            fontSize = 24.sp,
            color = Colores.textoPrincipal,
            modifier = Modifier.padding(16.dp)
        )

        if (generando) {
            Text(
                text = "Generando plan de ejercicios...",
                color = Colores.acentoPrincipal,
                fontSize = 16.sp
            )
        } else if (planEjercicio != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                backgroundColor = Colores.tarjetaFondo
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Nombre: ${planEjercicio?.nombre}",
                        fontSize = 18.sp,
                        color = Colores.textoPrincipal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Duración: ${planEjercicio?.duracionSemanas} semanas",
                        color = Colores.textoSecundario
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sesiones por semana: ${planEjercicio?.sesionesxSemana}",
                        color = Colores.textoSecundario
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Colores.textoSecundario)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Descripción: ${planEjercicio?.descripcion}",
                        color = Colores.textoSecundario,
                        fontSize = 12.sp
                    )
                }
            }
        } else {
            Text(
                text = "No hay plan de ejercicios generado aún",
                color = Colores.textoSecundario,
                fontSize = 14.sp,
                modifier = Modifier.padding(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                generando = true
                // Simulamos la generación del plan
                planEjercicio = PlanEjercicio(
                    nombre = "Plan de Fuerza y Masa",
                    duracionSemanas = 12,
                    sesionesxSemana = 4,
                    descripcion = "Plan personalizado enfocado en ganancia de masa muscular",
                    sesiones = emptyList(),
                    objetivo = "Hipertrofia"
                )
                generando = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(vertical = 8.dp),
            backgroundColor = Colores.acentoPrincipal
        ) {
            Text(
                text = "Generar Plan",
                color = Colores.textoBoton,
                fontSize = 16.sp
            )
        }

        Button(
            onClick = onVolverAlInicio,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(vertical = 8.dp),
            backgroundColor = Colores.botonSecundario
        ) {
            Text(
                text = "Volver al Inicio",
                color = Colores.textoPrincipal,
                fontSize = 16.sp
            )
        }
    }
}
