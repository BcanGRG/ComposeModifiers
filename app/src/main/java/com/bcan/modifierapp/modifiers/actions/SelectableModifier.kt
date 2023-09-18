package com.bcan.modifierapp.modifiers.actions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Selectable kullanıldığı modifier'ın seçilebilir olmasını sağlar.
 *
 * Aslında radio button gibi 2 seçeneğe sahip olan bir yapı oluşturulmasını sağlar.
 *
 * Temelde 4 tane parametre alır.
 *
 * @param selected Item'ın seçilip seçilmediğini belirtir.
 * @param enabled Item'ın aktif olup olmadığını kontrol eder.
 * @param role Kullanıcı arayüzündeki erişilebilirliği ayarlar.
 * @param onClick Tıklama fonksiyonu
 *
 * @param interactionSource Kullanıcının ekran interaksiyonlarını dinliyor
 * @param indication Tıklama işlevindeki görünüm
 *
 * */
@Preview(showBackground = true)
@Composable
fun SelectableModifier() {
    Column {
        DefaultSelectable()
        IndicationSelectable()
        CustomRadioButton()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSelectable() {

    val option1 = Color.Red
    val option2 = Color.Blue
    var selectedOption by remember { mutableStateOf(option1) }

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(option1, option2).forEach { color ->
                val selected = selectedOption == color
                Box(
                    Modifier
                        .size(100.dp)
                        .background(color = color)
                        .selectable(
                            selected = selected,
                            enabled = true,
                            role = Role.Button,
                            onClick = { selectedOption = color }
                        )
                )
            }
        }
        Text("Selected: $selectedOption")
    }
}

@Preview(showBackground = true)
@Composable
fun IndicationSelectable() {

    val option1 = Color.Black
    val option2 = Color.Gray
    var selectedOption by remember { mutableStateOf(option1) }

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(option1, option2).forEach { color ->
                val selected = selectedOption == color
                Box(
                    Modifier
                        .size(100.dp)
                        .background(color = color)
                        .selectable(
                            selected = selected,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                bounded = true,
                                radius = 40.dp,
                                color = Color.White
                            ),
                            onClick = { selectedOption = color }
                        )
                )
            }
        }
        Text("Selected: $selectedOption")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomRadioButton() {

    var isChecked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),

            ) {
            val radioSize = 50.dp
            Surface(
                modifier = Modifier
                    .size(radioSize)
                    .selectable(
                        selected = isChecked,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = true,
                            radius = radioSize / 2
                        ),
                        enabled = true,
                        role = Role.Checkbox,
                        onClick = { isChecked = !isChecked }
                    ),
                shape = RoundedCornerShape(radioSize / 2),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                if (isChecked) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check Icon",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}