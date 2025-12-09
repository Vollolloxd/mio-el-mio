package com.example.vollollomia.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vollollomia.models.dominio.nutricion.AlimentoDetectado
import com.example.vollollomia.models.dominio.nutricion.DatosNutricion
import com.example.vollollomia.models.dominio.gemini.ClienteGemini
import com.example.vollollomia.ui.tema.Colores

@Composable
fun PantallaEscanerAlimentos(
    onVolverAlInicio: () -> Unit
) {
    var alimento by remember { mutableStateOf<AlimentoDetectado?>(null) }
    var cargando by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colores.fondoPrincipal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Escáner de Alimentos",
            fontSize = 24.sp,
            color = Colores.textoPrincipal,
            modifier = Modifier.padding(16.dp)
        )

        if (cargando) {
            Text(
                text = "Analizando imagen...",
                color = Colores.acentoPrincipal,
                fontSize = 16.sp
            )
        } else if (alimento != null) {
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
                        text = "Alimento detectado: ${alimento?.nombreAlimento}",
                        fontSize = 18.sp,
                        color = Colores.textoPrincipal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Calorías: ${alimento?.calorias} kcal",
                        color = Colores.textoSecundario
                    )
                    Text(
                        text = "Proteínas: ${alimento?.proteinas}g",
                        color = Colores.textoSecundario
                    )
                    Text(
                        text = "Carbohidratos: ${alimento?.carbohidratos}g",
                        color = Colores.textoSecundario
                    )
                    Text(
                        text = "Grasas: ${alimento?.grasas}g",
                        color = Colores.textoSecundario
                    )
                }
            }
        }

        if (mensajeError.isNotEmpty()) {
            Text(
                text = "Error: $mensajeError",
                color = Colores.colorError,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        BotonEscaner(
            texto = "Capturar Foto",
            habilitado = !cargando,
            onClick = {
                cargando = true
                mensajeError = ""
                // Aquí iría la lógica de cámara
                // Por ahora, simulamos una respuesta
                alimento = AlimentoDetectado(
                    nombreAlimento = "Manzana",
                    calorias = 52.0,
                    proteinas = 0.3,
                    carbohidratos = 13.8,
                    grasas = 0.2,
                    fibra = 2.4,
                    sodio = 2.0,
                    potasio = 195.0
                )
                cargando = false
            }
        )

        BotonEscaner(
            texto = "Volver al Inicio",
            onClick = onVolverAlInicio
        )
    }
}

@Composable
fun BotonEscaner(
    texto: String,
    habilitado: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 8.dp),
        enabled = habilitado,
        backgroundColor = Colores.acentoPrincipal
    ) {
        Text(
            text = texto,
            color = Colores.textoBoton,
            fontSize = 16.sp
        )
    }
}
