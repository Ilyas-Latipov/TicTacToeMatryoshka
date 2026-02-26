package ru.tictac.tictactoe_matryoshka

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color


fun clickPlayer1Button(
    button: Players,
    set: SetValue,
    pl1buttons: List<Pl1>
) {
    when {
        button.id != set.oldBoardId -> {
            pl1buttons.forEach {if (button.id != it.id){it.enabled.value = false}}
            clickPlayerButtonSet(button, set)
        }

        else -> {
            clickPlayerButtonRetry(button, set)
            pl1buttons.forEach {it.enabled.value = true}
        }
    }
}


fun clickPlayer2Button(
    button: Players,
    set: SetValue,
    pl2buttons: List<Pl2>
) {
    when {
        button.id != set.oldBoardId -> {
            pl2buttons.forEach {if (button.id != it.id){it.enabled.value = false}}
            clickPlayerButtonSet(button, set)
        }

        else -> {
            clickPlayerButtonRetry(button, set)
            pl2buttons.forEach {it.enabled.value = true}
        }
    }
}

fun clickPlayerButtonSet(
    button: Players,
    set: SetValue,
) {
    button.count.value--
    set.id = button.id
    set.oldBoardId = button.id
}

fun clickPlayerButtonRetry(
    button: Players,
    set: SetValue,
) {
    button.count.value++
    set.id = ""
    set.oldBoardId = ""
}

fun clickBoardButton(
    button: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    pl1buttons: List<Pl1>,
    pl2buttons: List<Pl2>,
    state: MutableState<GameState>,
    theme: Theme
) {
    when (set.id) {
        "" -> {
            if (button.sizeNow.value == 0f) {
                return
            }
            clickBoardButtonRearrange(
                button,
                set,
                buttonsBoard,
                pl1buttons,
                pl2buttons
            )
        }

        else -> clickBoardButtonSet(
            button,
            set,
            buttonsBoard,
            pl1buttons,
            pl2buttons,
            state,
            theme
        )
    }
}

fun clickBoardButtonSet(
    button: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    pl1buttons: List<Pl1>,
    pl2buttons: List<Pl2>,
    state: MutableState<GameState>,
    theme: Theme
) {
    if (set.id[0].toString().toInt() * 0.28 < button.sizeNow.value) {
        return
    }
    decryptor(set.id, button)
    if (set.oldBoardId != button.id) {
        set.whoWalk = !set.whoWalk
    }
    set.id = ""
    set.oldBoardId = ""
    buttonsBoard.forEach {
        it.enabled.value = true
        it.buttonBackground.value = theme.themeBackNow.value
    }
    whoWalk(set.whoWalk, pl1buttons, pl2buttons)
    win(buttonsBoard, state)
}


fun clickBoardButtonRearrange(
    button: BoardModel,
    set: SetValue,
    buttonsBoard: List<BoardModel>,
    pl1buttons: List<Pl1>,
    pl2buttons: List<Pl2>
) {
    when {
        set.whoWalk && button.colorNow.value == Color.Blue -> return
        !set.whoWalk && button.colorNow.value == Color.Red -> return
    }
    set.oldBoardId = button.id
    buttonsBoardEnabled(button, buttonsBoard)
    when {
        set.whoWalk -> pl1buttons.forEach { it.enabled.value = false }
        else -> pl2buttons.forEach { it.enabled.value = false }
    }
    when {
        button.lvl3 != "" -> {
            set.id = button.lvl3
            button.lvl3 = ""
            button.buttonBackground.value = Color.Green
            when {
                button.lvl2 != "" -> decryptor(button.lvl2, button)
                button.lvl1 != "" -> decryptor(button.lvl1, button)
                else -> decryptor("0U", button)
            }
        }

        button.lvl2 != "" -> {
            set.id = button.lvl2
            button.lvl2 = ""
            button.buttonBackground.value = Color.Green
            when {
                button.lvl1 != "" -> decryptor(button.lvl1, button)
                else -> decryptor("0U", button)
            }
        }

        else -> {
            set.id = button.lvl1
            button.lvl1 = ""
            decryptor("0U", button)
            button.buttonBackground.value = Color.Green
        }
    }
}


fun buttonsBoardEnabled(button: BoardModel, buttons: List<BoardModel>) {
    buttons.forEach {
        if (button.id !in it.neighbors) {
            it.enabled.value = false
            it.buttonBackground.value = Color.Gray
        }
    }
}

fun decryptor(id: String, button: BoardModel) {
    when (id[0]) {
        '1' -> {
            button.sizeNow.value = 0.3f
            button.lvl1 = id
        }

        '2' -> {
            button.sizeNow.value = 0.6f
            button.lvl2 = id
        }

        '3' -> {
            button.sizeNow.value = 0.9f
            button.lvl3 = id
        }

        else -> button.sizeNow.value = 0f
    }
    when (id[1]) {
        'R' -> button.colorNow.value = Color.Red
        'B' -> button.colorNow.value = Color.Blue
        else -> button.colorNow.value = Color.Unspecified
    }
}

fun whoWalk(whoWalk: Boolean, pl1buttons: List<Pl1>, pl2buttons: List<Pl2>) {
    when {
        whoWalk -> {
            pl2buttons.forEach { it.enabled.value = false }
            pl1buttons.forEach {
                it.enabled.value = true
                if (it.count.value == 0) {
                    it.enabled.value = false
                }
            }
        }

        else -> {
            pl1buttons.forEach { it.enabled.value = false }
            pl2buttons.forEach {
                it.enabled.value = true
                if (it.count.value == 0) {
                    it.enabled.value = false
                }
            }
        }
    }
}

fun checkWin(board: List<BoardModel>, color: Color): Boolean {
    val winCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    return winCombinations.any { combo ->
        combo.all { index -> board[index].colorNow.value == color }
    }
}

fun win(board: List<BoardModel>, state: MutableState<GameState>) {
    when {
        checkWin(board, Color.Red) -> state.value = GameState.GameOver("Красные победили!")
        checkWin(board, Color.Blue) -> state.value= GameState.GameOver("Синие победили!")
    }
}

fun restart(
    state: MutableState<GameState>,
    board: List<BoardModel>,
    player1: List<Pl1>,
    player2: List<Pl2>,
    set: SetValue
) {
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
        player.enabled.value = false
    }
    set.id = ""
    set.oldBoardId = ""
    set.whoWalk = true
    set.color.value = Color.Unspecified
    set.size.value = 0f
    state.value= GameState.Playing
}


