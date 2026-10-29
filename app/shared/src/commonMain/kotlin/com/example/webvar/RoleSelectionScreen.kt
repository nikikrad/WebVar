package com.example.webvar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoleSelectionScreen(onRoleSelected: (String) -> Unit) {
    // Палитра розовых оттенков
    val backgroundPink = Color(0xFFFCE4EC) // Очень светло-розовый (Pink 50)
    val primaryPink = Color(0xFFE91E63)    // Насыщенный розовый (Pink 500)
    val cardBackground = Color(0xFFFFFFFF) // Белый для плашки

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundPink),
        contentAlignment = Alignment.Center
    ) {
        // Плашка (Card)
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f), // Ширина карточки 70% от ширины экрана (уменьшено на 30%)
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackground)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Текст
                Text(
                    text = "Кто вы?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryPink,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Кнопки
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { onRoleSelected("Воровайка") },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink),
                        modifier = Modifier.weight(1f) // Чтобы кнопки были одинаковой ширины (если нужно), но можно и без weight
                    ) {
                        Text("Воровайка", color = Color.White, fontSize = 16.sp)
                    }

                    Button(
                        onClick = { onRoleSelected("Варвара") },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Варвара", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
