package com.example.readscape.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookDetailScreen(book: Book) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Content of the book detail screen
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = book.author, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = book.description, fontSize = 14.sp)
        }
    }
}
