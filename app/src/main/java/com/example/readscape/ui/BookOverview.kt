package com.example.readscape.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readscape.ReadScapeAppBar
import com.example.readscape.model.Book
import com.example.readscape.model.BookService
import com.example.readscape.model.VolumeInfo
import com.example.readscape.ui.theme.ReadscapeTheme
import androidx.compose.foundation.lazy.LazyListScope
import com.example.readscape.model.BooksApiResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookOverviewScreen(bookService: BookService) {
    val booksState = remember { mutableStateOf<List<Book>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = bookService.getBooks("android", 10)
            if (response.isSuccessful) {
                val books = BooksApiResponse?.items?.mapNotNull { item -> item.toBook() }
                if (books != null) {
                    booksState.value = books
                }
            } else {
                // Handle API error
                // Show error message or retry logic
            }
        } catch (e: Exception) {
            // Handle network error
            // Show error message or retry logic
        }
    }

    Scaffold(
        topBar = {
            ReadScapeAppBar(
                canNavigateBack = false,
                navigateUp = { /* Handle navigate up action */ }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                item {
                    Text(
                        text = "Book Overview",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                items(booksState.value) { book ->
                    BookItem(book = book)
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        },
        bottomBar = {
            BottomNavigationBar()
        }
    )
}

@Composable
fun BookItem(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { /* Handle book item click */ },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Book cover image
            /*
            Image(
                painter = painterResource(id = book.coverImageResId),
                contentDescription = book.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )*/

            // Book details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = book.volumeInfo.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.volumeInfo.authors.toString(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    // TODO: Implement bottom navigation bar
}