package com.example.myway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myway.LocalData.listaDeLugares
import com.example.myway.ui.theme.MyWayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWayTheme {
                MyWayApp()
            }
        }
    }
}

@Composable
fun MyWayApp() {
    var telaAtual by remember { mutableStateOf("inicial") }
    var categoriaSelecionada by remember { mutableStateOf("") }
    var localSorteado by remember { mutableStateOf<Local?>(null) }

    when (telaAtual) {
        "inicial" -> {
            TelaInicialPlaceholder(onStartClick = { telaAtual = "categorias" })
        }
        "categorias" -> {
            TelaCategorias(onCategoriaClick = { cat ->
                categoriaSelecionada = cat
                val listaFiltrada = listaDeLugares.filter { it.categoria == cat }
                if (listaFiltrada.isNotEmpty()) {
                    localSorteado = listaFiltrada.random()
                    telaAtual = "resultado"
                }
            },
                onBackClick = { telaAtual = "inicial" }
            )
        }
        "resultado" -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF911642)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LocalCard(local = localSorteado!!)

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            localSorteado = listaDeLugares.filter { it.categoria == categoriaSelecionada }.random()
                        },
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBE7E7))
                    ) {
                        Text("start", color = Color(0xFF911642), style = MaterialTheme.typography.titleLarge)
                    }

                    TextButton(onClick = { telaAtual = "categorias" }) {
                        Text("Voltar para Categorias", color = Color.White.copy(alpha = 0.8f))
                    }
                }
            }
        }
    }
}

@Composable
fun TelaInicialPlaceholder(onStartClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.amor_sp),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStartClick,
                modifier = Modifier.size(130.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9A113F))
            ) {
                Text("start", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            }
        }
    }
}

@Composable
fun TelaCategorias(onCategoriaClick: (String) -> Unit, onBackClick: () -> Unit) {
    val categorias = listOf("Cafeterias", "Restaurantes", "Parques", "Bares", "Centros Culturais")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF911642))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Escolha uma categoria", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        categorias.forEach { cat ->
            Button(
                onClick = { onCategoriaClick(cat) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB34064))
            ) {
                Text(cat, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onBackClick) {
            Text("< Voltar para o Início", color = Color.White)

            }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun LocalCard(local: Local) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = local.imagemRes),
                contentDescription = local.nome,
                modifier = Modifier.height(280.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = local.nome, style = MaterialTheme.typography.headlineSmall, color = Color.Black)
                Text(text = local.endereco, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyWayTheme {
        MyWayApp()
    }
}