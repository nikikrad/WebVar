package com.example.webvar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondQuestionScreen(onYesClicked: (noClicksCount: Int) -> Unit) {
    val backgroundPink = Color(0xFFFCE4EC)
    val primaryPink = Color(0xFFE91E63)
    val cardBackground = Color(0xFFFFFFFF)

    var noClicks by remember { mutableStateOf(0) }

    // Анимация масштаба: при каждом нажатии отнимаем 10% от размера.
    // coerceAtMost(0.9f) и coerceAtLeast(0.0f) не дадут кнопке уйти в минус
    val noButtonScale by animateFloatAsState(
        targetValue = (1f - (noClicks * 0.1f)).coerceAtLeast(0.0f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundPink),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f), // На телефонах лучше занять 90% экрана
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
                    text = "Расскажешь, что я вчера сказал не так?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryPink,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onYesClicked(noClicks) },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink)
                        // Убрал weight(1f)
                    ) {
                        Text("Дааааа", color = Color.White, fontSize = 16.sp)
                    }

                    // Если кнопка уменьшилась до 0, можно ее вообще не рисовать, 
                    // но scale(0f) просто скроет её.
                    Button(
                        onClick = { noClicks++ },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink),
                        modifier = Modifier.scale(noButtonScale) // Оставил только scale, убрал weight(1f)
                    ) {
                        Text("Неть!!!!!", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
