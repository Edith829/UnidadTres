package com.example.pruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text(text = "Bienvenido a mi Segundo Activity",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center)
        }
    }
}