package com.codecoffee.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codecoffee.mvvm.login.ui.LoginScreen
import com.codecoffee.mvvm.login.ui.LoginViewModel
import com.codecoffee.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    LoginScreen(LoginViewModel())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Inicio de Sesion",
        fontSize = 24.sp, fontWeight = FontWeight.Bold,
        color=Color.Magenta,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().
        padding(top = 20.dp),
        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMTheme {
        Greeting("Android")
    }
}