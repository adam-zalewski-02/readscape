package com.example.readscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.readscape.data.BookDatabase
import com.example.readscape.model.UserDao
import com.example.readscape.ui.LoginScreen
import com.example.readscape.ui.theme.ReadscapeTheme

class MainActivity : ComponentActivity() {
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDao = BookDatabase.getInstance(this).userDao()

        setContent {
            ReadscapeTheme() {
                ReadScapeApp(userDao)
            }
        }
    }
}
