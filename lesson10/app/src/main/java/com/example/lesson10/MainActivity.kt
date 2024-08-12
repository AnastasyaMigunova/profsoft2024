package com.example.lesson10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.lesson10.ui.navigation.EDIT_PROFILE_SCREEN_ROUTE
import com.example.lesson10.ui.navigation.HOME_SCREEN_ROUTE
import com.example.lesson10.ui.navigation.NavHost
import com.example.lesson10.ui.navigation.PROFILE_SCREEN_ROUTE
import com.example.lesson10.ui.theme.Lesson10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson10Theme {
                val navController = rememberNavController()

               NavHost(navController, HOME_SCREEN_ROUTE)
            }
        }
    }
}