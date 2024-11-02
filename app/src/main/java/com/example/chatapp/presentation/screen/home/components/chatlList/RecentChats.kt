package com.example.chatapp.presentation.screen.home.components.chatlList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.presentation.screen.home.components.bottomNav.BottomSheetSwipe
import com.example.chatapp.ui.theme.OffWhite

@Composable
fun RecentChats() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(OffWhite)
    ) {
        BottomSheetSwipe(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 15.dp),
        )
        LazyColumn(
            modifier = Modifier.padding(top = 30.dp, bottom = 15.dp)
        ) {
            itemsIndexed(contacts) { index, contact ->
                ChatCard(
                    contactInfo = contact,
                    onClick = { },
                    showDivider = index != 0
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun RecentChatsPreview() {
    RecentChats()
}
