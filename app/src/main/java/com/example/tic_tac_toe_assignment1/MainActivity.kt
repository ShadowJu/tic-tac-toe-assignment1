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
import com.example.tic_tac_toe_assignment1.ui.theme.Tictactoeassignment1Theme

class MainActivity : ComponentActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button
    private var buttons = arrayOfNulls<Button>(9)
    private var xTurn = true
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout)
        statusText = findViewById(R.id.statusText)
        playAgainButton = findViewById(R.id.playAgainButton)

        for (i in 0 until 9) {
            buttons[i] = gridLayout.getChildAt(i) as Button
            buttons[i]?.setOnClickListener { onButtonClick(it as Button, i) }
        }

        // Play Again Button
        playAgainButton.setOnClickListener { resetGame() }

    }

    private fun onButtonClick(button: Button, index: Int) {
        if (button.text.isNotEmpty() || gameOver) return

        button.text = if (xTurn) "X" else "O"
        statusText.text = if (xTurn) "Player O's turn" else "Player X's turn"
        xTurn = !xTurn

    }
    private fun resetGame() {
        buttons.forEach { it?.text = "" }
        statusText.text = "Player X's turn"
        xTurn = true
        gameOver = false
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tictactoeassignment1Theme {
        Greeting("Android")
    }
}
