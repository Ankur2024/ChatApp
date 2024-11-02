package com.example.chatapp.presentation.screen.home.components.header

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.presentation.screen.home.components.chatlList.contacts


@Composable
fun ViewStory() {
    LazyRow(
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        item {
            StoryLayout()
            Spacer(modifier = Modifier.width(8.dp))
        }
        items(contacts, key = { it.id }) {
            UserStoryLayout(contactInfo = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoryLayoutPreview() {
    ViewStory()
}