package com.bcan.modifierapp.modifiers.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
@Composable
fun SelectableModifier(){
    val option1 = Color.Red
    val option2 = Color.Blue
    var selectedOption by remember { mutableStateOf(option1) }
    Column {
        Row {
            listOf(option1, option2).forEach { color ->
                val selected = selectedOption == color
                Box(
                    Modifier
                        .size(100.dp)
                        .background(color = color)
                        .selectable(
                            selected = selected,
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(),
                            onClick = {
                                selectedOption = color
                            }
                        )
                )
            }
        }
        Text("Selected: $selectedOption")

    }
}

