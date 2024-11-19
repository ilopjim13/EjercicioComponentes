package com.example.ejerciciocomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejerciciocomponentes.ui.theme.EjercicioComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioComponentesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComponentesBasicos(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ComponentesBasicos(name: String, modifier: Modifier = Modifier) {
    var switch by remember { mutableStateOf(false) }

    var check1 by remember { mutableStateOf(false) }
    var check2 by remember { mutableStateOf(false) }
    var check3 by remember { mutableStateOf(false) }

    var button by remember { mutableStateOf(false) }

    val checkeados = remember { mutableStateListOf<Int>() }
    Column(modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.DarkGray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Selector", modifier.padding(start = 16.dp), fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.White)
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "opciones",
                modifier.padding(end = 16.dp),
                tint = Color.White
            )
        }

        Spacer(Modifier.fillMaxWidth().height(1.dp).border(1.dp, Color.Black))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Elige los objetivos", modifier = Modifier.padding(bottom = 16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = check1, onCheckedChange = {
                    check1 = !check1
                    if(!check1) checkeados.remove(1)
                    else checkeados.add(1)
                    button = false
                })
                Text(text = "Objetivo 1")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = check2, onCheckedChange = {
                    check2 = !check2
                    if(!check2) checkeados.remove(2)
                    else checkeados.add(2)
                    button = false
                })
                Text(text = "Objetivo 2")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = check3, onCheckedChange = {
                    check3 = !check3
                    if(!check3) checkeados.remove(3)
                    else checkeados.add(3)
                    button = false
                })
                Text(text = "Objetivo 3")
            }


            Switch(checked = switch, onCheckedChange = {
                switch =  !switch
                button = false
            }, modifier = Modifier.padding(top = 20.dp))



            if (switch) {
                Button(onClick = { button = !button }, Modifier.padding(bottom = 56.dp, top = 20.dp)) {
                    Text("Pulsar")
                }

            }


        }



        if (button && switch) {
            Column(
                Modifier
                    .width(200.dp)
                    .height(120.dp)
                    .border(1.dp, Color.Black)
                    .background(Color.LightGray)
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val objetivo = checkeados.minOrNull()
                Text(
                    text = when (objetivo) {
                        1 -> "Objetivo 1"
                        2 -> "Objetivo 2"
                        3 -> "Objetivo 3"
                        else -> ""
                    }
                )
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
fun ComponentesBasicosPreview() {
    EjercicioComponentesTheme {
        ComponentesBasicos("Android")
    }
}