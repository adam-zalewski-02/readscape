package com.example.readscape.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readscape.R
import com.example.readscape.ui.components.customButton
import com.example.readscape.ui.theme.GoldenAmber
import com.example.readscape.ui.theme.MidnightIndigo
import com.example.readscape.ui.theme.ReadscapeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogInButtonClicked: (String, String) -> Unit,
    onSignUpButtonClicked: () -> Unit,
    onValueChangedEmail: (String) -> Unit,
    onValueChangedPassword: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(MidnightIndigo)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 49.dp, vertical = 77.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Readscape logo",
                modifier = Modifier
                    .size(147.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(percent = 14))
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(0.4f),
                        shape = RoundedCornerShape(percent = 14)
                    )
            )
            Text(
                text = "Readscape",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        onValueChangedEmail as (String) -> Unit
                    },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White)
                )
                Spacer(modifier = Modifier.padding(7.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        onValueChangedPassword as (String) -> Unit
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Spacer(modifier = Modifier.weight(2f))
            customButton(onClick = { onLogInButtonClicked(email, password) }, buttonText = "Log in", color = GoldenAmber)
            Spacer(modifier = Modifier.weight(0.5f))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "New to Readscape? ",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Sign up",
                    color = GoldenAmber,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{ onSignUpButtonClicked() }
                )
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    ReadscapeTheme {
        LoginScreen(
            onLogInButtonClicked = { email, password -> {} },
            onSignUpButtonClicked = {},
            onValueChangedEmail = {},
            onValueChangedPassword = {}
        )
    }
}
