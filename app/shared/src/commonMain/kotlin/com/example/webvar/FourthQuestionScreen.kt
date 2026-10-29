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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FourthQuestionScreen(onApologySelected: (String) -> Unit) {
    val backgroundPink = Color(0xFFFCE4EC)
    val primaryPink = Color(0xFFE91E63)
    val cardBackground = Color(0xFFFFFFFF)

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
                    text = "Что примете в качестве извенений?",
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
                        onClick = { onApologySelected("Шоколадку") },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink)
                        // Убрал weight(1f)
                    ) {
                        Text("Шоколадку", color = Color.White, fontSize = 16.sp)
                    }

                    Button(
                        onClick = { onApologySelected("Пизды") },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPink)
                        // Убрал weight(1f)
                    ) {
                        Text("Пизды", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
