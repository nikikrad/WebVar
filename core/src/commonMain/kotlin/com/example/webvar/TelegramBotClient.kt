package com.example.webvar

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TelegramMessageRequest(
    @SerialName("chat_id") val chatId: String,
    val text: String
)

class TelegramBotClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    private val botToken = "8778516173:AAHzMHO7-P83Om7rC2qapw0t-qNtc9T4lt0"
    private val defaultChatId = "530429076"
    
    // В локальной разработке мы использовали http://localhost:8081/api/telegram/send
    // Теперь указываем URL сервера, который будет доступен в интернете.
    // Если вы будете хостить Wasm и Сервер на одной платформе (например, Railway или Render), 
    // часто достаточно указать относительный путь или URL вашего будущего бэкенда.
    // Пока оставим заглушку, которую вы замените на реальный URL после деплоя сервера.
    private val apiUrl = "URL_ВАШЕГО_СЕРВЕРА/api/telegram/send" 
    
    suspend fun sendMessage(text: String, chatId: String = defaultChatId): Boolean {
        return try {
            val response = client.post(apiUrl) {
                contentType(ContentType.Application.Json)
                setBody(TelegramMessageRequest(chatId = chatId, text = text))
            }
            response.status.isSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
