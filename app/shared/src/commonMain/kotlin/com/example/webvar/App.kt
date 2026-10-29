package com.example.webvar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

enum class AppScreen { ROLE_SELECTION, SECOND_QUESTION, THIRD_QUESTION, FOURTH_QUESTION, SENDING_RESULT }

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf(AppScreen.ROLE_SELECTION) }
    
    // Переменные для сохранения результатов локально
    var selectedRole by remember { mutableStateOf("") }
    var noClicksCount by remember { mutableStateOf(0) }
    var userDescription by remember { mutableStateOf("") }
    var selectedApology by remember { mutableStateOf("") }

    MaterialTheme {
        when (currentScreen) {
            AppScreen.ROLE_SELECTION -> {
                RoleSelectionScreen(
                    onRoleSelected = { role ->
                        selectedRole = role
                        currentScreen = AppScreen.SECOND_QUESTION
                    }
                )
            }
            AppScreen.SECOND_QUESTION -> {
                SecondQuestionScreen(
                    onYesClicked = { clicks ->
                        noClicksCount = clicks
                        currentScreen = AppScreen.THIRD_QUESTION
                    }
                )
            }
            AppScreen.THIRD_QUESTION -> {
                ThirdQuestionScreen(
                    onConfirmed = { text ->
                        userDescription = text
                        currentScreen = AppScreen.FOURTH_QUESTION
                    }
                )
            }
            AppScreen.FOURTH_QUESTION -> {
                FourthQuestionScreen(
                    onApologySelected = { apology ->
                        selectedApology = apology
                        currentScreen = AppScreen.SENDING_RESULT
                    }
                )
            }
            AppScreen.SENDING_RESULT -> {
                SendingResultScreen(
                    role = selectedRole,
                    noClicks = noClicksCount,
                    description = userDescription,
                    apology = selectedApology
                )
            }
        }
    }
}