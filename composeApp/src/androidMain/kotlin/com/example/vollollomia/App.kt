package com.example.vollollomia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.vollollomia.ui.navegacion.NavegadorApp
import com.example.vollollomia.ui.tema.Colores

@Composable
fun App() {
    MaterialTheme(
        colorScheme = androidx.compose.material3.lightColorScheme(
            primary = Colores.RosaClaro,
            secondary = Colores.AzulPastel,
            tertiary = Colores.VerdeMenta,
            background = Colores.GrisClaro,
            surface = Colores.Blanco,
            onPrimary = Colores.Negro,
            onSecondary = Colores.Negro,
            onBackground = Colores.Negro,
            onSurface = Colores.Negro
        )
    ) {
        NavegadorApp()
    }
}
