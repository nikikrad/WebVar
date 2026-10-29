package com.example.webvar

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

// Создаем HTTP-клиент для сервера, который будет делать запросы к Telegram
val telegramClient = HttpClient(CIO) {
    install(ClientContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Настраиваем CORS, чтобы браузер (WasmJS) не блокировал запросы к нашему серверу
    install(CORS) {
        anyHost() // Разрешаем запросы с любых хостов
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }

    // Настраиваем сериализацию JSON на сервере
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    routing {
        get("/") {
            call.respondText(sayHello("Ktor"))
        }

        // Роут, который будет принимать запросы от WasmJS приложения и пересылать их в Telegram
        post("/api/telegram/send") {
            try {
                // Читаем запрос от клиента
                val request = call.receive<TelegramMessageRequest>()

                // Ваши учетные данные
                val botToken = "8778516173:AAHzMHO7-P83Om7rC2qapw0t-qNtc9T4lt0"
                val apiUrl = "https://api.telegram.org/bot${botToken}/sendMessage"

                // Отправляем запрос в Telegram от лица нашего сервера
                val response = telegramClient.post(apiUrl) {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }

                if (response.status.isSuccess()) {
                    call.respond(HttpStatusCode.OK, mapOf("status" to "success"))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, mapOf("status" to "error"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, mapOf("status" to "error"))
            }
        }
    }
}
