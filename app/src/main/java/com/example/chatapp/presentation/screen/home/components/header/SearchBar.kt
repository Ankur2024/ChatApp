package com.example.chatapp.presentation.screen.home.components.header

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(searchText: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onTextChange,
        label = {Text(text = "Search...")},
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}

