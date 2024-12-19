package com.example.tic_tac_toe_assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var statusText: TextView
    private lateinit var restartButton: Button
    private var buttons = arrayOfNulls<Button>(9)
    private var isXTurn = true
    private var isGameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        statusText = findViewById(R.id.statusText)
        restartButton = findViewById(R.id.restartButton)

        for (i in 0 until gridLayout.childCount) {
            buttons[i] = gridLayout.getChildAt(i) as Button
            buttons[i]?.setOnClickListener { onButtonClick(it as Button, i) }
        }

        // Play Again Button
        restartButton.setOnClickListener { resetGame() }

    }

    private fun onButtonClick(button: Button, index: Int) {
        if (button.text.isNotEmpty()) return

        button.text = if (isXTurn) "X" else "O"
        statusText.text = if (isXTurn) "Player O's turn" else "Player X's turn"
        isXTurn = !isXTurn

    }
    private fun resetGame() {
        buttons.forEach { it?.text = "" }
        statusText.text = "Player X's turn"
        isXTurn = true
        isGameOver = false
    }
}