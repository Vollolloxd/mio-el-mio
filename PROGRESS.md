# Progreso de Desarrollo - Aplicación Mio-el-Mio

## Visión General
Aplicación Android para reconocimiento de alimentos mediante Gemini API, con gestión de nutrición, planes de ejercicio y dieta personalizada.

## Requisitos del Usuario
- Detectar alimentos desde fotos usando Gemini API
- Ver nutrientes completos: calorias, proteínas, carbohidratos, grasas
- Registrar datos del usuario: altura, peso, objetivo (masa muscular, hipertrofia, definición)
- Generar plan calórico, rutinas de ejercicio y dieta
- Persistencia de datos entre sesiones
- Diseño colorido con colores pastel y múltiples pantallas
- **TODOS los nombres de archivos, variables y funciones en ESPAÑOL**

## Progreso Actual

### ✅ Capa de Dominio (Dominio)
Modelos de negocio con herencia y polimorfismo:

1. **DatosNutricion.kt** (`dominio/nutricion/`)
   - `InformacionNutricional` - Clase base abierta
   - `AlimentoDetectado` - Alimentos identificados por Gemini
   - `RegistroNutricionalDiario` - Seguimiento diario
   - `ObjetivoFisico` - Sealed class con opciones: GananciaDeMasa, HiperTrofia, Definicion, Mantenimiento
   - `PerfilUsuario` - Datos personales con métodos de cálculo (IMC, tasa metabólica, gasto calórico)
   - `MetaNutricional` - Metas personalizadas

2. **PlanEjercicio.kt** (`dominio/ejercicio/`)
   - `TipoEjercicio` - Sealed class: Cardio, Musculacion, Flexibilidad, Funcional
   - `IntensidadEjercicio` - Sealed class: Baja, Moderada, Alta
   - `Ejercicio` - Ejercicios individuales con cálculo de calorias quemadas
   - `SesionEntrenamiento` - Sesiones con múltiples ejercicios
   - `PlanEjercicio` - Planes personalizados con progreso
   - `HistorialEjercicio` - Seguimiento de entrenamientos

3. **PlanDieta.kt** (`dominio/dieta/`)
   - `TipoDieta` - Sealed class: DeficitCalorico, Mantenimiento, Superavit, IntervalFasting
   - `Receta` - Recetas con ingredientes y preparación
   - `Ingrediente` - Detalles de ingredientes
   - `Comida` - Comidas del día (desayuno, almuerzo, etc.)
   - `DiaDieta` - Días de dieta con múltiples comidas
   - `PlanDieta` - Planes dietarios personalizados
   - `HistorialDieta` - Seguimiento de dietas

### ✅ Capa de Persistencia (Persistencia)
Almacenamiento de datos:

1. **PreferenciasUsuario.kt** (`persistencia/preferencias/`)
   - Usa SharedPreferences para guardar datos del usuario
   - Métodos: `guardarPerfilUsuario()`, `obtenerPerfilUsuario()`, `limpiarPreferencias()`
   - Almacena: ID, nombre, altura, peso, edad, sexo, objetivo, nivel de actividad

## Próximos Pasos

### 📋 Capa de UI/Presentación (ui/)
A crear:
- `pantallas/PantallaInicio.kt` - Pantalla principal con navegación
- `pantallas/PantallaEscanerAlimentos.kt` - Cámara + Gemini API
- `pantallas/PantallaDatosUsuario.kt` - Registro de perfil
- `pantallas/PantallaDieta.kt` - Visualización de plan dietario
- `pantallas/PantallaEjercicios.kt` - Rutinas de ejercicio
- `tema/Colores.kt` - Paleta de colores pastel
- `navegacion/NavegadorApp.kt` - Sistema de navegación

### 🔧 Integración Gemini API
A crear en `dominio/gemini/`:
- `ApiGemini.kt` o `ClienteGemini.kt`
- Función: `analizarImagenAlimento(imagen: Bitmap): AlimentoDetectado`
- Función: `generarPlanEjercicio(...): PlanEjercicio`
- Función: `generarPlanDieta(...): PlanDieta`

### 📱 Configuración del Proyecto KMP
- AndroidMain: Impl de cámara y SharedPreferences
- CommonMain: Lógica común
- App.kt / MainActivity.kt: Punto de entrada
- Gradle: Dependencias de Jetpack Compose, Gemini SDK

## Notas Importantes
- Todos los archivos, funciones y variables están en ESPAÑOL
- Se sigue la arquitectura en capas: dominio -> persistencia -> ui
- Uso de sealed classes y data classes para mayor seguridad de tipos
- Métodos de cálculo integrados en los modelos
- SharedPreferences para persistencia simple; puede escalarse a Room DB si es necesario

## Estado General
**Arquitectura Base: 50% completada**
- ✅ Modelos de dominio
- ✅ Capa de persistencia básica
- ⏳ UI/Pantallas (pendiente)
- ⏳ Integración Gemini (pendiente)
- ⏳ Navegación y temas (pendiente)

## Progreso Actualizado (Sesion 2)

### ✅ Integración de Gemini API (Completada)
1. **ClienteGemini.kt** (`dominio/gemini/`)
   - Cliente para conectar con Gemini API v1beta
   - Métodos para:
     - `analizarImagenAlimento(bitmap)`: Detecta alimentos de fotos
     - `generarPlanEjercicio(perfil)`: Crea planes personalizados
     - `generarPlanDieta(perfil, calorias)`: Genera planes dietéticos

2. **UtilidadesGemini.kt** (`dominio/gemini/`)
   - Funciones de parseo y validación de respuestas JSON
   - Ejemplos de datos para pruebas
   - Cálculo de macronutrientes

### ✅ Capa de UI/Tema (En Progreso)
1. **Colores.kt** (`ui/tema/`)
   - Paleta completa de colores pastel
   - Colores primarios: Rosa, Azul, Verde
   - Colores para nutrientes: Proteínas, Carbohidratos, Grasas, Fibra
   - Colores para estados: Exito, Advertencia, Error, Info
   - Tema claro por defecto

## Estado General Actualizado
**Arquitectura: 65% completada**
- ✅ Modelos de dominio (nutrición, ejercicio, dieta)
- ✅ Capa de persistencia (SharedPreferences)
- ✅ Integración Gemini API (estructura y cliente)
- ✅ Sistema de colores y tema visual
- ⏳ Pantallas/Composables (en progreso)
- ⏳ Navegación (pendiente)

## Próximos Pasos
1. Crear pantallas principales:
   - PantallaInicio.kt
   - PantallaEscanerAlimentos.kt
   - PantallaDatosUsuario.kt
   - PantallaDieta.kt
   - PantallaEjercicios.kt
2. Implementar navegación con Compose Navigation
3. Integrar cámara para escaneo de alimentos
4. Completar llamadas HTTP a Gemini API con parseo JSON
