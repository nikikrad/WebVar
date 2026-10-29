package com.example.webvar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TelegramScreen(telegramBotClient: TelegramBotClient = remember { TelegramBotClient() }) {
    var textInput by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }
    
    // Получаем CoroutineScope привязанный к жизненному циклу компонента
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Отправить сообщение в Telegram",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Сообщение") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSending
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (textInput.isNotBlank()) {
                    isSending = true
                    statusMessage = "Отправка..."
                    
                    // Запускаем suspend-функцию в корутине
                    coroutineScope.launch {
                        val success = telegramBotClient.sendMessage(text = textInput)
                        isSending = false
                        if (success) {
                            statusMessage = "✅ Сообщение успешно отправлено!"
                            textInput = "" // Очищаем поле после успешной отправки
                        } else {
                            statusMessage = "❌ Ошибка при отправке. Проверьте консоль."
                        }
                    }
                }
            },
            enabled = textInput.isNotBlank() && !isSending,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isSending) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Отправка...")
            } else {
                Text("Отправить")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (statusMessage.isNotEmpty()) {
            Text(
                text = statusMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = if (statusMessage.startsWith("✅")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
