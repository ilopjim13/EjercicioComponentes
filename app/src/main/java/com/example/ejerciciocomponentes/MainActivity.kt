package com.example.ejerciciocomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
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
        Row {
            Text(text = "Esta es mi aplicacion")
        }

        Column {
            Row {
                Checkbox(checked = check1, onCheckedChange = {
                    check1 = !check1
                    if(check1) checkeados.remove(1)
                    else checkeados.add(1)
                    button = false
                })
                Text(text = "Objetivo 1")
            }
            Row {
                Checkbox(checked = check2, onCheckedChange = {
                    check2 = !check2
                    if(check2) checkeados.remove(2)
                    else checkeados.add(2)
                    button = false
                })
                Text(text = "Objetivo 2")
            }
            Row {
                Checkbox(checked = check3, onCheckedChange = {
                    check3 = !check3
                    if(check3) checkeados.remove(3)
                    else checkeados.add(3)
                    button = false
                })
                Text(text = "Objetivo 3")
            }


            Switch(checked = switch, onCheckedChange = {
                switch =  !switch
                button = false
            })



            if (switch) {
                Button(onClick = { button = !button }) {
                    Text("Pulsar")
                }

            }


        }


        if (button && switch) {
            val objetivo = checkeados.minOrNull()
            Column()  {
                Text(
                    text = when(objetivo) {
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