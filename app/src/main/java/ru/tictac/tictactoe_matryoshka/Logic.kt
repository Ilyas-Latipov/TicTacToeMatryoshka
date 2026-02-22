package ru.tictac.tictactoe_matryoshka

import androidx.compose.ui.graphics.Color
import android.util.Log

fun clickPlayerButton(button: Players, set: SetValue) {
    if (set.whoWalk && button.id[1] in "R" || !set.whoWalk && button.id[1] in "B") {
        set.selected = !set.selected
        if (!set.selected) {
            if (set.id != button.id) {
                set.selected = !set.selected
                return
            }
            button.count.value += 1
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

fun clickBoardButtonSet(button: BoardModel, set: SetValue) {
    if (set.selected) {
        set.selected = false
        set.whoWalk = !set.whoWalk
        button.colorNow.value = set.color.value
        button.sizeNow.value = set.size.value
        when (set.id[0]) {
            in "1" -> button.lvl1 = set.id
            in "2" -> button.lvl2 = set.id
            in "3" -> button.lvl3 = set.id
        }
    }
}

fun clickBoardButtonRearrange(button: BoardModel, set: SetValue) {
    if (set.whoWalk && button.colorNow.value == Color.Red ||
        !set.whoWalk && button.colorNow.value == Color.Blue
    ) {
        set.selected = !set.selected
        if (!set.selected) {
            if (set.id != button.id) {
                set.selected = !set.selected
                return
            }
            return
        }
        set.id = button.id
        set.color.value = button.colorNow.value
        set.size.value = button.sizeNow.value
        when {
            button.lvl1 != "" && button.lvl2 == "" && button.lvl3 == "" -> {
                TODO()
            }
//            TODO()
        }
    }
}