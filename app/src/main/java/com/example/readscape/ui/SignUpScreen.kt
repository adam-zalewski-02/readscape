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
fun SignupScreen(
    onLogInButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit
) {
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
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White)
                )
                Spacer(modifier = Modifier.padding(bottom = 14.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(bottom = 14.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text(text = "Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
            customButton(onClick = onSignUpButtonClicked, buttonText = "Sign up", color = GoldenAmber)
            Spacer(modifier = Modifier.padding(14.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Already have an account? ",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Log in",
                    color = GoldenAmber,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLogInButtonClicked() }
                )
            }
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    ReadscapeTheme {
        SignupScreen(
            onLogInButtonClicked = {},
            onSignUpButtonClicked = {}
        )
    }
}

