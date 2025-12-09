package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.models.dominio.nutricion.ObjetivoFisico
import com.example.vollollomia.ui.tema.Colores


@Composable
fun PantallaDatosUsuario(
    onVolverAlInicio: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf<ObjetivoFisico>(ObjetivoFisico.Definicion) }
    var nivelActividad by remember { mutableStateOf("moderado") }
    var guardado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.Fondo)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Datos del Usuario",
            fontSize = 24.sp,
            color = Colores.TextoPrincipal,
            modifier = Modifier.padding(16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                OutlinedTextField(
                    value = altura,
                    onValueChange = { altura = it },
                    label = { Text("Altura (cm)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                OutlinedTextField(
                    value = peso,
                    onValueChange = { peso = it },
                    label = { Text("Peso (kg)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Text(
                    text = "Objetivo Físico",
                    color = Colores.TextoPrincipal,
                    modifier = Modifier.padding(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf(
                        "Hipertrofia" to ObjetivoFisico.HiperTrofia,
                        "Definición" to ObjetivoFisico.Definicion,
                        "Ganancia de Masa" to ObjetivoFisico.GananciaDeMasa
                    ).forEach { (label, obj) ->
                        Button(
                            onClick = { objetivo = obj },
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(label, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (guardado) {
                    Text(
                        text = "Datos guardados correctamente",
                        color = Colores.ExitoVerde,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                guardado = true
                // Aquí se guardarían los datos
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Guardar Datos",
                color = Colores.Blanco,
                fontSize = 16.sp
            )
        }

        Button(
            onClick = onVolverAlInicio,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Volver al Inicio",
                color = Colores.TextoPrincipal,
                fontSize = 16.sp
            )
        }
    }
}
