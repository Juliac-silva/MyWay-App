package com.example.roller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.roller.ui.theme.RollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite preenchimento de tela inclusive a barra de status
        setContent {
            RollerTheme {
                RollerApp()
            }
        }
    }
}


// Conteúdo do APP
@Composable // Botao e Imagem
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize() // Preencher todo o espaço disponível
    .wrapContentSize(Alignment.Center)
) {

    //var numero_sorteado = 5
    var numero_sorteado by remember { mutableStateOf( 1) }

    var imagem_sorteada = when (numero_sorteado) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    var texto_sorteado = when (numero_sorteado) {
        1 -> "Lado 1"
        2 -> "Lado 2"
        3 -> "Lado 3"
        4 -> "Lado 4"
        5 -> "Lado 5"
        else -> "Lado 6"
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sorteio")
        Image(
            painter = painterResource(imagem_sorteada),
            contentDescription = "Lado 1 do Dado"

        )
        Button(onClick = {
            numero_sorteado = (1..6).random()
        }) {
            Text(stringResource(R.string.Sorteio))
        }
    }


}

// Visualização de como vai ficar a tela.
@Preview(showBackground = true)
@Composable
fun RollerApp() { // Conteudo Principal do APP
    DiceWithButtonAndImage()
}
