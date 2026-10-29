# Используем официальный образ с Java и Gradle для сборки
# Меняем версию на 8.10, так как Android-плагинам нужен более новый Gradle
FROM gradle:8.10-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Используем gradlew (Wrapper), который уже есть в вашем проекте. Он скачает именно ту версию Gradle,
# которая прописана в gradle-wrapper.properties (у вас там, судя по логам, 9.3.1).
RUN chmod +x ./gradlew
RUN ./gradlew :server:buildFatJar --no-daemon

# Используем легковесный образ с Java 17 для запуска сервера
FROM eclipse-temurin:17-jre-alpine
# Создаем директорию для приложения
RUN mkdir /app
# Копируем собранный JAR файл из этапа сборки
COPY --from=build /home/gradle/src/server/build/libs/server-all.jar /app/ktor-server.jar

# Открываем порт 8081 (на нем работает наш сервер)
EXPOSE 8081

# Указываем команду для запуска сервера
ENTRYPOINT ["java", "-jar", "/app/ktor-server.jar"]
