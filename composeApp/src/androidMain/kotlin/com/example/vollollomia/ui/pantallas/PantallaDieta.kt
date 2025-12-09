package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.dominio.dieta.PlanDieta
import com.example.vollollomia.dominio.dieta.Comida
import com.example.vollollomia.ui.tema.Colores

@Composable
fun PantallaDieta(
    onVolverAlInicio: () -> Unit
) {
    var planDieta by remember { mutableStateOf<PlanDieta?>(null) }
    var generando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.fondoPrincipal)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plan de Dieta",
            fontSize = 24.sp,
            color = Colores.textoPrincipal,
            modifier = Modifier.padding(16.dp)
        )

        if (generando) {
            Text(
                text = "Generando plan de dieta...",
                color = Colores.acentoPrincipal,
                fontSize = 16.sp
            )
        } else if (planDieta != null) {
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
                        text = "Tipo de Dieta: ${planDieta?.tipoDieta}",
                        fontSize = 18.sp,
                        color = Colores.textoPrincipal
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = Colores.textoSecundario)
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = "Días del Plan: ${planDieta?.dias?.size ?: 0}",
                        color = Colores.textoSecundario
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Objetivo Calórico: ${planDieta?.objetivoCaloriasDiarias} kcal/día",
                        color = Colores.textoSecundario
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Descripción: ${planDieta?.descripcion}",
                        color = Colores.textoSecundario,
                        fontSize = 12.sp
                    )
                }
            }
        } else {
            Text(
                text = "No hay plan de dieta generado aún",
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
                planDieta = PlanDieta(
                    tipoDieta = "Balanceada",
                    objetivoCaloriasDiarias = 2000.0,
                    descripcion = "Plan de dieta personalizado basado en tus objetivos",
                    dias = emptyList(),
                    recetas = emptyList()
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
