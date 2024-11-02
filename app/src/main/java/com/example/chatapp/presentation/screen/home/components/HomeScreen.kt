package com.example.chatapp.presentation.screen.home.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.chatapp.R
import com.example.chatapp.presentation.screen.home.components.bottomNav.BottomNav
import com.example.chatapp.presentation.screen.home.components.chatlList.RecentChats
import com.example.chatapp.presentation.screen.home.components.header.ProfileHeader
import com.example.chatapp.presentation.screen.home.components.header.SearchBar
import com.example.chatapp.presentation.screen.home.components.header.ViewStory

@Composable
fun HomeScreen(navController: NavController) {
    var searchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
                .fillMaxSize()
        ) {
            ProfileHeader(onSearchClicked = { searchVisible = !searchVisible })

            AnimatedVisibility(
                visible = searchVisible,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            ) {
                SearchBar(searchText, onTextChange = { searchText = it })
            }

            ViewStory()
            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.weight(1f)) {
                RecentChats()
            }
        }

        BottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )

        FloatingActionButton(
            onClick = { /* Handle new chat action */ },
            containerColor = Color.Black.copy(alpha = 0.8f),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 20.dp,
                bottomEnd = 0.dp
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-16).dp, y = (-110).dp)
                .size(60.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "new chat",
                tint = Color.White
            )
        }
    }
}




@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
