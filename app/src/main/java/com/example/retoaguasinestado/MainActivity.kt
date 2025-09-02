// Paquete de la app
package com.example.retoaguasinestado

// Importaciones necesarias para la actividad y elementos Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retoaguasinestado.ui.theme.RetoAguaSinEstadoTheme

// Actividad principal de la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite que el contenido ocupe toda la pantalla
        setContent {
            // Se aplica el tema visual de la app
            RetoAguaSinEstadoTheme (
                darkTheme = false,
                dynamicColor = false
            ) {
                // Se llama a la función principal de la app
                RetoAguaScreen()
            }
        }
    }
}

// Componente padre RetoAguaScreen() que maneja el estado
@Composable
fun RetoAguaScreen() {
    // Estados necesarios (esta parte sí es con estado)
    var peso by remember { mutableStateOf("") }
    var hizoEjercicio by remember { mutableStateOf(false) }
    var metaDiaria by remember { mutableIntStateOf(0) }
    var vasosTomados by remember { mutableIntStateOf(0) }

    val mlPorVaso = 250
    val mlConsumidos = vasosTomados * mlPorVaso

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "💧 Reto de Agua Diario 💧",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Campo de peso
        PesoInput(peso = peso, onPesoChange = { peso = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Switch de ejercicio
        EjercicioSwitch(
            hizoEjercicio = hizoEjercicio,
            onToggle = { hizoEjercicio = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón calcular meta
        BotonCalcularMeta(
            peso = peso,
            hizoEjercicio = hizoEjercicio,
            onMetaCalculada = {
                metaDiaria = it
                vasosTomados = 0
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar progreso si ya hay meta
        if (metaDiaria > 0) {
            ProgresoAgua(
                metaDiaria = metaDiaria,
                mlConsumidos = mlConsumidos,
                onSumarVaso = {
                    if (mlConsumidos < metaDiaria) vasosTomados++
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar mensaje final
            MetaFinal(
                mlConsumidos = mlConsumidos,
                metaDiaria = metaDiaria
            )
        }
    }
}

@Composable
fun PesoInput(peso: String, onPesoChange: (String) -> Unit) {
    OutlinedTextField(
        value = peso,
        onValueChange = onPesoChange,
        label = { Text("¿Cuál es tu peso? (kg)") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EjercicioSwitch(hizoEjercicio: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("¿Hiciste ejercicio hoy?")
        Switch(
            checked = hizoEjercicio,
            onCheckedChange = onToggle
        )
    }
}

@Composable
fun BotonCalcularMeta(peso: String, hizoEjercicio: Boolean, onMetaCalculada: (Int) -> Unit) {
    Button(
        onClick = {
            val pesoKg = peso.toIntOrNull()
            if (pesoKg != null) {
                val meta = (pesoKg * 35) + if (hizoEjercicio) 500 else 0
                onMetaCalculada(meta)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Calcular meta de agua")
    }
}

@Composable
fun ProgresoAgua(metaDiaria: Int, mlConsumidos: Int, onSumarVaso: () -> Unit) {
    Text(
        text = "🎯 Tu meta diaria es: $metaDiaria ml",
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Start
    )
    Text(
        text = "🥤 Has tomado: $mlConsumidos ml",
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Start
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onSumarVaso,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("+1 vaso (250 ml)")
    }
}

@Composable
fun MetaFinal(mlConsumidos: Int, metaDiaria: Int) {
    if (mlConsumidos >= metaDiaria) {
        Text(
            text = "✅ Meta alcanzada ✅",
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        val restante = metaDiaria - mlConsumidos
        Text(text = "Te faltan $restante ml. ¡Sigue así! 💪")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RetoAguaSinEstadoPreview() {
    RetoAguaSinEstadoTheme (
        darkTheme = false,
        dynamicColor = false
    ) {
        RetoAguaScreen()
    }
}