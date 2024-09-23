package com.example.provakotlin

import Estoque
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.provakotlin.ui.theme.ProvaKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvaKotlinTheme {
                // Navegação principal do aplicativo
                LayoutMain()
            }
        }
    }
}
