package com.example.readscape.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readscape.ui.theme.GoldenAmber
import com.example.readscape.ui.theme.ReadscapeTheme

@Composable
fun customButton(onClick: () -> Unit, buttonText: String, color: Color) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(percent = 14),
        modifier = Modifier
            .border(
                width = 1.dp,
                Color.White.copy(0.4f),
                shape = RoundedCornerShape(percent = 14)
            )
            .background(color = color, shape = RoundedCornerShape(percent = 14))
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Text(
            text = buttonText,
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 4.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    ReadscapeTheme {
        customButton(onClick = {}, buttonText = "Log in", color = GoldenAmber)
    }
}