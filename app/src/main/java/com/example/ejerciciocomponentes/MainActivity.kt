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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
                    ComponentesBasicos(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ComponentesBasicos(modifier: Modifier = Modifier) {
    var switch by remember { mutableStateOf(false) }
    var check1 by remember { mutableStateOf(false) }
    var check2 by remember { mutableStateOf(false) }
    var check3 by remember { mutableStateOf(false) }
    var button by remember { mutableStateOf(false) }
    val checkeados = remember { mutableStateListOf<Int>() }


    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Cabecera(modifier)

        Objetivos(check1, check2, check3, switch,
            onChangeValue1 = {
                check1 = !check1
                if (!check1) checkeados.remove(1)
                else checkeados.add(1)
                button = false
            },
            onChangeValue2 = {
                check2 = !check2
                if (!check2) checkeados.remove(2)
                else checkeados.add(2)
                button = false
            },
            onChangeValue3 = {
                check3 = !check3
                if (!check3) checkeados.remove(3)
                else checkeados.add(3)
                button = false
            },
            onChangeSwitch = {
                switch = !switch
                button = false
            },
            onClickButton = { button = !button }
        )


        TextObjetivos(button, switch, checkeados)

    }
}

@Composable
fun Cabecera(modifier: Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFB7AFAF)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Componentes",
            modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "opciones",
            modifier.padding(end = 16.dp),
        )
    }

    Spacer(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .border(0.5.dp, Color.Black)
    )
}


@Composable
fun Objetivos(
    check1: Boolean,
    check2: Boolean,
    check3: Boolean,
    switch: Boolean,
    onChangeValue1: (Boolean) -> Unit,
    onChangeValue2: (Boolean) -> Unit,
    onChangeValue3: (Boolean) -> Unit,
    onChangeSwitch: (Boolean) -> Unit,
    onClickButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Elige los objetivos", modifier = Modifier.padding(bottom = 16.dp))

        CheckObjetivos(check1, check2, check3, onChangeValue1, onChangeValue2, onChangeValue3)

        SwitchButtonObjetivos(switch, onChangeSwitch, onClickButton)

    }
}

@Composable
fun SwitchButtonObjetivos(
    switch: Boolean,
    onChangeSwitch: (Boolean) -> Unit,
    onClickButton: () -> Unit
) {
    Switch(
        checked = switch,
        onCheckedChange = onChangeSwitch,
        colors = customSwitchColors(),
        modifier = Modifier.padding(top = 20.dp)
    )

    if (switch) {
        Button(
            onClick = onClickButton,
            modifier = Modifier.padding(bottom = 56.dp, top = 20.dp),
            colors = customButtonColors()
        ) {
            Text("Pulsar")
        }

    }
}


@Composable
fun CheckObjetivos(
    check1: Boolean,
    check2: Boolean,
    check3: Boolean,
    onChangeValue1: (Boolean) -> Unit,
    onChangeValue2: (Boolean) -> Unit,
    onChangeValue3: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = check1,
            onCheckedChange = onChangeValue1,
            colors = customCheckboxColors()
        )
        Text(text = "Objetivo 1")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = check2,
            onCheckedChange = onChangeValue2,
            colors = customCheckboxColors()
        )
        Text(text = "Objetivo 2")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = check3,
            onCheckedChange = onChangeValue3,
            colors = customCheckboxColors()
        )
        Text(text = "Objetivo 3")
    }
}


@Composable
fun TextObjetivos(button: Boolean, switch: Boolean, checkeados: SnapshotStateList<Int>) {
    if (button && switch) {
        Column(
            Modifier
                .width(200.dp)
                .height(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFB7AFAF)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val objetivo = checkeados.minOrNull()
            Text(
                text = when (objetivo) {
                    1 -> "Objetivo 1"
                    2 -> "Objetivo 2"
                    3 -> "Objetivo 3"
                    else -> "No hay objetivos"
                }
            )
        }
    }
}

@Composable
fun customCheckboxColors() = CheckboxDefaults.colors(
    checkedColor = Color.Red,
    uncheckedColor = Color.Black,
    checkmarkColor = Color.Black,
    disabledCheckedColor = Color.Red,
    disabledUncheckedColor = Color.White,
    disabledIndeterminateColor = Color.Black
)


@Composable
fun customSwitchColors() = SwitchDefaults.colors(
    checkedThumbColor = Color.Red,
    uncheckedThumbColor = Color(0xFF949494),
    checkedTrackColor = Color(0xFF7C0707),
    uncheckedTrackColor = Color.White,
    disabledCheckedThumbColor = Color.Red,
    disabledUncheckedThumbColor = Color.White,
    disabledCheckedTrackColor = Color.Red,
    disabledUncheckedTrackColor = Color.White
)

@Composable
fun customButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Red,
    contentColor = Color.White,
    disabledContainerColor = Color.Red,
    disabledContentColor = Color.White
)


@Preview(showBackground = true)
@Composable
fun ComponentesBasicosPreview() {
    EjercicioComponentesTheme {
        ComponentesBasicos()
    }
}
