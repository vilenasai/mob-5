package com.example.tiptime

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        // Вводим 10 в поле Bill Amount
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")

        // Вводим 20 в поле Tip Percentage
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")

        // Проверяем, что появился текст с результатом
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists()
    }
}