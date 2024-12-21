package com.example.tic_tac_toe_assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.tic_tac_toe_assignment1.utils.GameUtils
import com.example.tic_tac_toe_assignment1.utils.StateEnum

class MainActivity : ComponentActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var statusText: TextView
    private lateinit var restartButton: Button
    private lateinit var board: Array<CharArray>

    private var isXTurn = true
    private var currentPlayer: Char = 'X'
    private var buttons = arrayOfNulls<Button>(9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        statusText = findViewById(R.id.statusText)
        restartButton = findViewById(R.id.restartButton)

        board = Array(3) { CharArray(3) { ' ' } }
        statusText.text = getString(R.string.player_turn, currentPlayer)

        for (i in 0 until gridLayout.childCount) {
            buttons[i] = gridLayout.getChildAt(i) as Button
            buttons[i]?.setOnClickListener { onButtonClick(it as Button) }
        }
        restartButton.setOnClickListener { resetGame() }
    }

    private fun onButtonClick(button: Button) {
        if (button.text.isEmpty()) {
            useGameLogic(button)
            isXTurn = !isXTurn
        }
    }

    private fun useGameLogic(button: Button) {
        val tag = button.tag.toString().split(",").map { it.toInt() }
        val row = tag[0]
        val col = tag[1]
        if (GameUtils.makeMove(board, currentPlayer, row, col)) {
            button.text = currentPlayer.toString()
            checkBoard()
        }
    }

    private fun checkBoard(){
        val gameState = GameUtils.checkGameState(board)

        statusText.text = when (gameState) {
            StateEnum.X -> {
                setGameButtonsEnabled(false)
                getString(R.string.player_won, "X")
            }

            StateEnum.O -> {
                setGameButtonsEnabled(false)
                getString(R.string.player_won, "O")
            }

            StateEnum.TIE -> getString(R.string.tie)
            else -> {
                currentPlayer = if (isXTurn) 'O' else 'X'
                getString(R.string.player_turn, currentPlayer)
            }
        }
    }

    private fun resetGame() {
        board = Array(3) { CharArray(3) { ' ' } }
        buttons.forEach { it?.text = "" }
        currentPlayer = 'X'
        statusText.text = getString(R.string.player_turn, currentPlayer)
        isXTurn = true

        setGameButtonsEnabled(true)
    }


    private fun setGameButtonsEnabled(enabled: Boolean) {
        val gridLayout: GridLayout = findViewById(R.id.gridLayout)
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.isEnabled = enabled
        }
    }
}