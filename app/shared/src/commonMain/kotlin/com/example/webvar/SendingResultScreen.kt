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
fun SendingResultScreen(
    role: String,
    noClicks: Int,
    description: String,
    apology: String,
    telegramBotClient: TelegramBotClient = remember { TelegramBotClient() }
) {
    val backgroundPink = Color(0xFFFCE4EC)
    val primaryPink = Color(0xFFE91E63)
    val cardBackground = Color(0xFFFFFFFF)

    var statusMessage by remember { mutableStateOf("Отправляем результаты Никитке...") }
    var isSuccess by remember { mutableStateOf<Boolean?>(null) }

    // Запускаем отправку один раз при показе экрана
    LaunchedEffect(Unit) {
        val message = """
            |Новый ответ!
            |Кто вы?: $role
            |Расскажешь, что я вчера сказал не так?: Дааааа (Но кнопка 'Неть!!!!!' была нажата $noClicks раз)
            |Что было сказано Никиткой:
            |$description
            |Что примете в качестве извенений?: $apology
        """.trimMargin()
        
        val success = telegramBotClient.sendMessage(text = message)
        isSuccess = success
        if (success) {
            statusMessage = "Успех! Твои ответы отправлены 💖"
        } else {
            statusMessage = "Ой, что-то пошло не так 😢\nПроверь подключение к интернету."
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundPink),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f), // Ширина карточки 70% от ширины экрана
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackground)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isSuccess == null) {
                    CircularProgressIndicator(
                        color = primaryPink, 
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                
                Text(
                    text = statusMessage,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isSuccess == false) Color.Red else primaryPink,
                    textAlign = TextAlign.Center, // Выравнивание текста внутри компонента Text по центру
                    modifier = Modifier.fillMaxWidth() // Занимаем всю ширину, чтобы textAlign сработал корректно
                )
            }
        }
    }
}
