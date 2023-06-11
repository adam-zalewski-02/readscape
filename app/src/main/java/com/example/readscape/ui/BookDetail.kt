package com.example.readscape.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readscape.model.book.VolumeInfo
import com.example.readscape.R

@SuppressLint("ComposableNaming", "UnusedComposable")
@Composable
fun BookDetailScreen(book: VolumeInfo) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Content of the book detail screen
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = book.authors.toString(), fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            book.description?.let { Text(text = it, fontSize = 14.sp) }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { shareBookTitle(book.title, context) }) {
                Text(text = "Share")
            }
        }
    }
}

@SuppressLint("UnnecessaryComposableCall")
fun shareBookTitle(title: String, context: Context) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, "Book Title")
        putExtra(Intent.EXTRA_TEXT, title)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
