package com.example.vollollomia.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vollollomia.ui.pantallas.PantallaInicio
import com.example.vollollomia.ui.pantallas.PantallaEscanerAlimentos
import com.example.vollollomia.ui.pantallas.PantallaDatosUsuario
import com.example.vollollomia.ui.pantallas.PantallaDieta
import com.example.vollollomia.ui.pantallas.PantallaEjercicios

object Rutas {
    const val INICIO = "inicio"
    const val ESCANEO = "escaneo"
    const val DATOS_USUARIO = "datos_usuario"
    const val DIETA = "dieta"
    const val EJERCICIOS = "ejercicios"
}

@Composable
fun NavegadorApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {
        composable(Rutas.INICIO) {
            PantallaInicio(
                onNavEscaneo = { navController.navigate(Rutas.ESCANEO) },
                onNavDatos = { navController.navigate(Rutas.DATOS_USUARIO) },
                onNavDieta = { navController.navigate(Rutas.DIETA) },
                onNavEjercicios = { navController.navigate(Rutas.EJERCICIOS) }
            )
        }
        
        composable(Rutas.ESCANEO) {
            PantallaEscanerAlimentos(
                onVolverAlInicio = { navController.navigate(Rutas.INICIO) }
            )
        }
        
        composable(Rutas.DATOS_USUARIO) {
            PantallaDatosUsuario(
                onVolverAlInicio = { navController.navigate(Rutas.INICIO) }
            )
        }
        
        composable(Rutas.DIETA) {
            PantallaDieta(
                onVolverAlInicio = { navController.navigate(Rutas.INICIO) }
            )
        }
        
        composable(Rutas.EJERCICIOS) {
            PantallaEjercicios(
                onVolverAlInicio = { navController.navigate(Rutas.INICIO) }
            )
        }
    }
}
