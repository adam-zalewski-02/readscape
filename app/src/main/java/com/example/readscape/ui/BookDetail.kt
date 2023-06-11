package com.example.readscape.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Context
import android.net.Uri
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
import java.net.URLEncoder

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

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { googleSearchBookTitle(book.title, context) }) {
                Text(text = "Google Search")
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

@SuppressLint("UnnecessaryComposableCall")
fun googleSearchBookTitle(title: String, context: Context) {
    val query = URLEncoder.encode(title, "UTF-8")
    val url = "https://www.google.com/search?q=$query"
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    context.startActivity(intent)
}