package ru.tictac.tictactoe_matryoshka

import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

fun clickPlayerButton(button: Players, set: SetValue, buttons: List<Players>) {
    if (set.whoWalk && button.id[1] in "R" || !set.whoWalk && button.id[1] in "B") {
        set.selected = !set.selected
        buttons.forEach {if (button.id != it.id){it.enabled.value = false}}
        if (!set.selected) {
            button.count.value += 1
            buttons.forEach {it.enabled.value = true}
            return
        }
        button.count.value -= 1
        set.color.value = button.color.value
        set.id = button.id
        when (button.size.value) {
            70 -> set.size.value = 0.3f
            90 -> set.size.value = 0.6f
            110 -> set.size.value = 0.9f
        }

    }
}

fun clickBoardButtonSet(
    button: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    buttonsPlayers: List<Players>,
    winner: WinnerData
) {
    if (set.selected && set.size.value > button.sizeNow.value) {
        set.selected = false
        if (button.id != set.oldBoardId){set.whoWalk = !set.whoWalk}
        button.colorNow.value = set.color.value
        button.sizeNow.value = set.size.value
        buttonsBoardTrueEnabled(buttonsBoard, buttonsPlayers)
        if ((set.id).length == 2) {
            when (set.id[0]) {
                in "1" -> button.lvl1 = set.id
                in "2" -> button.lvl2 = set.id
                in "3" -> button.lvl3 = set.id
            }
        }
        win(buttonsBoard, winner)
    }
}

fun clickBoardButtonRearrange(
    button: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    buttonsPlayers: List<Players>
) {
    if (set.whoWalk && button.colorNow.value == Color.Red ||
        !set.whoWalk && button.colorNow.value == Color.Blue
    ) {
        set.selected = !set.selected
        buttonsBoardEnabled(button, buttonsBoard)
        buttonsPlayers.forEach {it.enabled.value = false}
        set.oldBoardId = button.id
        set.color.value = button.colorNow.value
        set.size.value = button.sizeNow.value
        when {
            button.lvl1 != "" && button.lvl2 == "" && button.lvl3 == "" -> {
                set.id = button.lvl1
                button.colorNow.value = Color.Unspecified
                button.sizeNow.value = 0f
                button.lvl1 = ""
                button.buttonBackground.value = Color.Green
            }
            button.lvl2 != "" && button.lvl1 == "" && button.lvl3 == "" -> {
                set.id = button.lvl2
                button.colorNow.value = Color.Unspecified
                button.sizeNow.value = 0f
                button.lvl2 = ""
                button.buttonBackground.value = Color.Green
            }
            button.lvl3 != "" && button.lvl1 == "" && button.lvl2 == "" -> {
                set.id = button.lvl3
                button.colorNow.value = Color.Unspecified
                button.sizeNow.value = 0f
                button.lvl3 = ""
                button.buttonBackground.value = Color.Green
            }
            button.lvl1 != "" && button.lvl2 != "" && button.lvl3 == "" -> {
                set.id = button.lvl2
                button.lvl2 = ""
                decryptor(button.lvl1, button)
                button.buttonBackground.value = Color.Green
            }
            button.lvl1 != "" && button.lvl2 == "" && button.lvl3 != "" -> {
                set.id = button.lvl3
                button.lvl3 = ""
                decryptor(button.lvl1, button)
                button.buttonBackground.value = Color.Green
            }
            button.lvl1 == "" && button.lvl2 != "" && button.lvl3 != "" -> {
                set.id = button.lvl3
                button.lvl3 = ""
                decryptor(button.lvl2, button)
                button.buttonBackground.value = Color.Green
            }
            button.lvl1 != "" && button.lvl2 != "" && button.lvl3 != "" -> {
                set.id = button.lvl3
                button.lvl3 = ""
                decryptor(button.lvl2, button)
                button.buttonBackground.value = Color.Green
            }
        }
    }
}

fun buttonsBoardEnabled(button: BoardModel, buttons: List<BoardModel>){
    buttons.forEach {
        if (button.id !in it.neighbors){
            it.enabled.value = false
            it.buttonBackground.value = Color.Gray
        }
    }
}

fun buttonsBoardTrueEnabled(buttonsBoard: List<BoardModel>, buttonsPlayers: List<Players>){
    buttonsBoard.forEach {it.enabled.value = true}
    buttonsPlayers.forEach {it.enabled.value = true}
    buttonsBoard.forEach {it.buttonBackground.value = Color.White}
}

fun decryptor(lvl: String, button: BoardModel){
    when (lvl[0]){
        '1' -> button.sizeNow.value = 0.3f
        '2' -> button.sizeNow.value = 0.6f
    }
    when (lvl[1]){
        'R' -> button.colorNow.value = Color.Red
        'B' -> button.colorNow.value = Color.Blue
    }
}

fun checkWin(board: List<BoardModel>, color: Color, ): Boolean {
    val winCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    return winCombinations.any { combo ->
        combo.all { index -> board[index].colorNow.value == color }
    }
}

fun win(board: List<BoardModel>, winner: WinnerData){
    when {
        checkWin(board, Color.Red) -> winner.winner.value = "Красные победили!"
        checkWin(board, Color.Blue) -> winner.winner.value = "Синие победили!"
        else -> winner.winner.value = ""
    }
}

fun restart(winner: WinnerData,
            board: List<BoardModel>,
            player1: List<Pl1>,
            player2: List<Pl2>,
            set: SetValue){
    board.forEach { cell ->
        cell.lvl1 = ""
        cell.lvl2 = ""
        cell.lvl3 = ""
        cell.colorNow.value = Color.Unspecified
        cell.sizeNow.value = 0f
        cell.buttonBackground.value = Color.White
        cell.enabled.value = true
    }
    player1.forEach { player ->
        player.count.value = 3
        player.enabled.value = true
    }
    player2.forEach { player ->
        player.count.value = 3
        player.enabled.value = true
    }
    set.id = ""
    set.oldBoardId = ""
    set.whoWalk = true
    set.selected = false
    set.color.value = Color.Unspecified
    set.size.value = 0f
    winner.winner.value = ""
}


