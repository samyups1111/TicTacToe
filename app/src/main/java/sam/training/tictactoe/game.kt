package sam.training.tictactoe

import java.util.*

fun main() {
    var player1 = "Player1"
    var player2 = "Player2"
    val scanner = Scanner(System.`in`)
    val gameBoard = arrayOf("_", "_", "_", "_", "_", "_", "_", "_", "_")
    var i = 0
    fun scoreCombo() = arrayOf(gameBoard.slice(0..2), gameBoard.slice(3..5), gameBoard.slice(6..8),
        gameBoard.slice(0..8 step 3), gameBoard.slice(1..8 step 3), gameBoard.slice(2..8 step 3),
        gameBoard.slice(0..8 step 4), gameBoard.slice(2..6 step 2))
    val xxx = listOf("X", "X", "X")
    val ooo = listOf("O", "O", "O")
    var xWins: Boolean = false
    var oWins: Boolean = false
    var playerOneTurn = false
    var symbol = "O"
    var gameInProgress = true
    val choicesUsed = mutableListOf<Int>()
    
    fun selectOpponent() {
        var numOfPlayers = 0
        println("How Many Players? (1 or 2)")
        if (scanner.hasNextInt()) {
            numOfPlayers = scanner.nextInt()
        }

        scanner.nextLine()
        if (numOfPlayers == 1 || numOfPlayers == 2) {
            println("Player 1 Enter Name: -> ")
            player1 = scanner.nextLine()
        } else {
            println("Error: wrong input.")
            selectOpponent()
        }
        if (numOfPlayers == 2) {
            println("Player 2 Enter Name: -> ")
            player2 = scanner.nextLine()
        }
    }

    fun displayBoard() {
        println("-".repeat(9))
        println("| ${gameBoard[0]} ${gameBoard[1]} ${gameBoard[2]} |")
        println("| ${gameBoard[3]} ${gameBoard[4]} ${gameBoard[5]} |")
        println("| ${gameBoard[6]} ${gameBoard[7]} ${gameBoard[8]} |")
        println("-".repeat(9))
    }

    fun checkForWinner() {
        for (combo in scoreCombo()) {
            if (combo.toTypedArray().contentEquals(xxx.toTypedArray())) xWins = true
            else if (combo.toTypedArray().contentEquals(ooo.toTypedArray())) oWins = true
        }
    }

    fun checkResults() {
        if (xWins) {
            println("$player1 wins!!!")
            gameInProgress = false
        } else if (oWins) {
            println("$player2 wins")
            gameInProgress = false
        } else if ("_" !in gameBoard) {
            println("Draw")
            gameInProgress = false
        }
    }

    fun changeSymbol() {
        if (playerOneTurn) {
            symbol = "X"
        } else {
            symbol = "O"
        }
    }

    fun nextTurn() {
        if (playerOneTurn) {
            playerOneTurn = false
            changeSymbol()
            println("$player2's turn")

        } else {
            playerOneTurn = true
            changeSymbol()
            println("$player1's turn")
        }
    }

    fun enterInput() {
        var choice = 0
        println("Enter cell number (1-9): -> ")
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt()
            scanner.nextLine()
        }
        if (choice !in 1..9) {
            println("Invalid Entry. Please try again.")
            enterInput()
        } else if (choice in choicesUsed) {
            println("Occupied cell. Please try again.")
            enterInput()
        } else {
            choicesUsed.add(choice)
            gameBoard[choice - 1] = symbol
        }
    }

    fun start() {
        selectOpponent()
        displayBoard()
        while (gameInProgress) {
            nextTurn()
            enterInput()
            displayBoard()
            checkForWinner()
            checkResults()
        }
    }

    start()
}