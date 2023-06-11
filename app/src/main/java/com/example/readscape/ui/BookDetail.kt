package com.example.readscape.ui

import android.widget.ScrollView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readscape.model.book.VolumeInfo

@Composable
fun BookDetailScreen(book: VolumeInfo) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Content of the book detail screen
        LazyColumn(
                modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            item {
                Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = book.authors.toString(), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Description:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                book.description?.let { Text(text = it, fontSize = 14.sp) }
            }
        }

    }
}

