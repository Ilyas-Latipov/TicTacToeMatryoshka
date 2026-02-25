//package ru.tictac.tictactoe_matryoshka
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import java.util.Objects
//
//@Composable
//fun Winner(
//    state: MutableState<GameState>,
//    board: List<BoardModel>,
//    player1: List<Pl1>,
//    player2: List<Pl2>,
//    set: SetValue,
//    colorRow1: MutableState<Color>,
//    colorRow2: MutableState<Color>) {
////    if (set.whoWalk){
////        colorRow1.value = Color.DarkGray
////        colorRow2.value = Color.Gray
////    }
////    else{
////        colorRow2.value = Color.DarkGray
////        colorRow1.value = Color.Gray
////    }
//    if (winner.winner.value != "") {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Transparent),
//            contentAlignment = Alignment.Center
//        ) {
//            Button(
//                onClick = {
//                    restart(winner, board, player1, player2, set)
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .clip(RoundedCornerShape(45.dp)),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black,
//                    contentColor = Color.White,
//                    disabledContentColor = Color.DarkGray
//                ),
//            ) {
//                Text(
//                    text = winner.winner.value,
//                    fontSize = 45.sp,
//                    lineHeight = 50.sp,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//
//    }
//}