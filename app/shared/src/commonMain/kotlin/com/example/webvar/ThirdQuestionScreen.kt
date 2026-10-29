package com.example.webvar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThirdQuestionScreen(onConfirmed: (String) -> Unit) {
    val backgroundPink = Color(0xFFFCE4EC)
    val primaryPink = Color(0xFFE91E63)
    val cardBackground = Color(0xFFFFFFFF)

    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundPink),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.95f), // Поле для ввода текста, на телефоне лучше занять почти весь экран
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackground)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Опишите - Что было сказано Никиткой",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryPink,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Введите ваш ответ здесь...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), // Делаем поле ввода достаточно большим
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryPink,
                        focusedLabelColor = primaryPink,
                        cursorColor = primaryPink
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onConfirmed(description) },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryPink),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = description.isNotBlank() // Кнопка активна только если текст введен
                ) {
                    Text(
                        text = "С моих слов записано верно, мною прочитано",
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}
